# Skin Gorithm - Machine Learning Notebook

## 1. Facial Skin Status Analyze

### INTRODUCTION
Analysis of facial skin status, such as acne, dark circles, flek and wrinkles.

### DATASET
We collect datasets manually from google images. The distribution as follows :
- Acne's dataset 
- Dark circle's dataset 
- Eye's dataset 
- Face skin's dataset 
- Flek's dataset 
- Wrinkles's dataset

### WORKFLOW STAGES
1. Cropping your face images dataset, you can use [our notebook](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Cropping%20Face%20Images.ipynb) to cropping your face images dataset 
2. Labelling your images dataset using ImageJ. You can download in [here](https://imagej.nih.gov/ij/download.html)
3. Resize your input images to 256x256, you can use [our notebook](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Resize%20Image%20to%20256x256.ipynb) to resize your images
4. Stack your image using ImageJ software
5. Training the model, we using ResUNet architecture as our baseline. But, you can start training your own model immediately by modifying the Model Section or load your own model. There are some files of our notebook:
   - [Acne Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Acne%20Skin%20Segmentation/Acne%20Segmentation%20-%20ResUnet.ipynb)
   - [Dark Circle Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Dark%20Circle%20Segmentation/Dark%20Circle%20Segmentation%20-%20ResUnet.ipynb)
   - [Eye Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Eye%20Segmentation/Eye%20Segmentation%20-%20ResUnet.ipynb)
   - [Face Skin Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Face%20Skin%20Segmentation/Face%20Skin%20Segmentation%20-%20ResUnet.ipynb)
   - [Flek Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Flek%20Segmentation/Flek%20Segmentation%20-%20ResUnet.ipynb)
   - [Wrinkles Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Wrinkles%20Segmentation/Wrinkles%20Segmentation%20-%20ResUnet.ipynb)
6. Load the model and convert model to tflite format, you can use [our notebook](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Load%20and%20Convert%20to%20TFLite.ipynb) to convert your model


## 2. Face Shape Classification

