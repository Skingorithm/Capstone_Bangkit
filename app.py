import tensorflow as tf
from keras.models import load_model
import segmentation_models as sm
import matplotlib.image as img
import numpy as np
import base64
import io
from PIL import Image
import os
import cv2
import sys
from flask import Flask, jsonify, request
# from flask import jsonify
import os

app = Flask(__name__)
# WINDOW_SIZE = 60
# trainMaxIndex = 876

@app.route("/skingorithm/predict", methods=["POST"])
def predict2():
    data = {"success": False, "output": []}
#     try:
    params = request.get_json()
    if params is None:
        return jsonify(data)
    if(params != None):
        finalinput = preproccess_input(params['input'])
        output_model = modelrun(finalinput)

        data["success"] = True
        data["output"] = output_model
#     except:
#         print("Get exception")
    return jsonify(data)

#function resize dan crop

def get_cropped_image_if_2_eyes(image_path):
    face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')
    eye_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_eye.xml')
    img = np.array(image_path)
    faces = face_cascade.detectMultiScale(img, 1.3,5)
    for (x,y,w,h) in faces:
        roi_color = img[y:y+h, x:x+(w)]
        roi_color = cv2.resize(roi_color, (256, 256))
        eyes = eye_cascade.detectMultiScale(roi_color)
        if len(eyes) >= 2:
            return roi_color

#Decode base64 to image
def preproccess_input(input):
    input = base64.b64decode(input)
    input = Image.open(io.BytesIO(input))
    input = get_cropped_image_if_2_eyes(input)
    input = input/255.0

    finalinput = np.expand_dims(input, 0)
    return finalinput

def modelrun(finalinput):
    #FaceSkin
    FS_prediction = FS_model.predict(finalinput)
    FS_array = np.array(FS_prediction)
    FS = np.sum(FS_array > 0.5)

    #Acne 
    AC_prediction = AC_model.predict(finalinput)
    AC_array = np.array(AC_prediction)
    AC = np.sum(AC_array > 0.5)

    #Black Spot 
    BS_prediction = BS_model.predict(finalinput)
    BS_array = np.array(BS_prediction)
    BS = np.sum(BS_array > 0.5)

    #Wrinkle
    WR_prediction = WR_model.predict(finalinput)
    WR_array = np.array(WR_prediction)
    WR = np.sum(WR_array > 0.5)

    #Eye Area
    EA_prediction = EA_model.predict(finalinput)
    EA_array = np.array(EA_prediction)
    EA = np.sum(EA_array > 0.5)

    #Panda Eye
    PE_prediction = PE_model.predict(finalinput)
    PE_array = np.array(PE_prediction)
    PE = np.sum(PE_array > 0.5)

    #persen
    acne = round(100 - ((AC/FS) * 100),2)
    bspot = round(100 -((BS/FS) * 100),2)
    wrinkle = round(100 - ((WR/FS) * 100),2)
    peye = round(100 - ((PE/EA) * 100),2)
    average = round(((acne+bspot+wrinkle+peye)/4),2)

    output = dict()
    output['acne'] = acne
    output['bspot'] = bspot
    output['wrinkle'] = wrinkle
    output['peye'] = peye
    output['average'] = average

    return output

def load_models(fs_dict, ac_dict, bs_dict, wr_dict, ea_dict, pe_dict):
    FS_model = load_model(fs_dict, custom_objects={'binary_crossentropy_plus_jaccard_loss':sm.losses.bce_jaccard_loss, 'iou_score':sm.metrics.iou_score}, compile=False)
    AC_model = load_model(ac_dict, custom_objects={'binary_crossentropy_plus_jaccard_loss':sm.losses.bce_jaccard_loss, 'iou_score':sm.metrics.iou_score}, compile=False)
    BS_model = load_model(bs_dict, custom_objects={'binary_crossentropy_plus_jaccard_loss':sm.losses.bce_jaccard_loss, 'iou_score':sm.metrics.iou_score}, compile=False)
    WR_model = load_model(wr_dict, custom_objects={'binary_crossentropy_plus_jaccard_loss':sm.losses.bce_jaccard_loss, 'iou_score':sm.metrics.iou_score}, compile=False)
    EA_model = load_model(ea_dict, custom_objects={'binary_crossentropy_plus_jaccard_loss':sm.losses.bce_jaccard_loss, 'iou_score':sm.metrics.iou_score}, compile=False)
    PE_model = load_model(pe_dict, custom_objects={'binary_crossentropy_plus_jaccard_loss':sm.losses.bce_jaccard_loss, 'iou_score':sm.metrics.iou_score}, compile=False)
    return FS_model, AC_model, BS_model, WR_model, EA_model, PE_model


if __name__ == "__main__":
    # Load Model

    fspath = './modelML/skin_ResUnet.h5'
    acpath = './modelML/acne_ResUnet.h5'
    bspath = './modelML/flek_ResUnet.h5'
    wrpath = './modelML/wrinkles_ResUnet.h5'
    eapath = './modelML/eye_ResUnet.h5'
    pepath = './modelML/panda_ResUnet.h5'
    FS_model, AC_model, BS_model, WR_model, EA_model, PE_model = load_models(fspath, acpath, bspath, wrpath, eapath, pepath)

    app.run(host='0.0.0.0', port=int(os.environ.get("PORT", 8080)))
