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

### Workflow Stages
- Resize our input images to 256x256
- We are using ResUNet architecture so there will be encoding and decoding path.
- We are creating keras model with residual block on the encoding and decoding path. We also need to concatenate each convolutional block and input it on the decoding path.
- Training the model
- Plot the IOU score, precision, and loss
- Save the model

### LAUNCH
There are some files of model facial skin segmentation:
- [Acne Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Acne%20Skin%20Segmentation/Acne%20Segmentation%20-%20ResUnet.ipynb)
- [Dark Circle Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Dark%20Circle%20Segmentation/Dark%20Circle%20Segmentation%20-%20ResUnet.ipynb)
- [Eye Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Eye%20Segmentation/Eye%20Segmentation%20-%20ResUnet.ipynb)
- [Face Skin Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Face%20Skin%20Segmentation/Face%20Skin%20Segmentation%20-%20ResUnet.ipynb)
- [Flek Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Flek%20Segmentation/Flek%20Segmentation%20-%20ResUnet.ipynb)
- [Wrinkles Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Wrinkles%20Segmentation/Wrinkles%20Segmentation%20-%20ResUnet.ipynb)


## 2. Face Shape Classification
