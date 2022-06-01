# Skin Gorithm - Machine Learning

## 1. Facial Skin Problem Segmentation

### INTRODUCTION

### DATASET
We collect datasets manually from google images. The distribution as follows :
- Acne's dataset 
- Dark circle's dataset 
- Eye's dataset 
- Face skin's dataset 
- Flek's dataset 
- Wrinkle's dataset

### WORKFLOW STAGES
1. Cropping your face images dataset, you can use our [notebook this]() to cropping your face images dataset 
2. Labelling your images dataset using ImageJ or Label-Studio
3. Resize your input images to 256x256
4. Stack your image using ImageJ 
5. Training the model. You can start training your own model immediately by modifying the Model Section or load your own model. There are some files of our notebook, we using ResUNet architecture as our baseline:
   - [Acne Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Acne%20Skin%20Segmentation/Acne%20Segmentation%20-%20ResUnet.ipynb)
   - [Dark Circle Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Dark%20Circle%20Segmentation/Dark%20Circle%20Segmentation%20-%20ResUnet.ipynb)
   - [Eye Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Eye%20Segmentation/Eye%20Segmentation%20-%20ResUnet.ipynb)
   - [Face Skin Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Face%20Skin%20Segmentation/Face%20Skin%20Segmentation%20-%20ResUnet.ipynb)
   - [Flek Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Flek%20Segmentation/Flek%20Segmentation%20-%20ResUnet.ipynb)
   - [Wrinkles Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Wrinkles%20Segmentation/Wrinkles%20Segmentation%20-%20ResUnet.ipynb)
6. Plot the IOU score, precision, and loss
7. Save the model

### LAUNCH


## 2. Face Shape Classification
