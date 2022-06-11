# C22-PS180 [Skin Gorithm] - Machine Learning

## Facial Skin Status Analyzer

### Introduction
Recently, the trend of skincare products has increased rapidly. In 2021 the demand for skincare in Indonesia is 70% higher than the previous year. This is due to many things, starting with the higher demand for skincare products, various skin problems, and also because of the rampant influence from skincare influencers that encourage people to be more aware of their skin health. Along with the increasing demand and usage of skincare products, there are still a lot of people who lack knowledge about the skincare things, a lot of people are still confused about how to choose the best skincare for themselves, and they are still not pretty sure is the skincare they using is effective for their skin or not. 

Therefore, this project is built an app to analysis of facial skin status, such as acne, flek, panda eyes, and wrinkles using Convolutional Neural Network-based Deep Learning Technology with UNet architecture and ResNEt34 as our backbone (encoder). In addition, our app also provides features ingredient analyze to help users can understand more about the skincare function, pros, and cons based on the ingredients, alarm feature to remind the users of their skincare daily usage, and the most important thing they can see their progress in their skincare journey!

### Dataset
We collect datasets manually from google images. The distribution as follows :
- Acne's dataset: 40 
- Dark circle's dataset: 50 
- Eye's dataset: 60
- Face skin's dataset: 80
- Flek's dataset: 43
- Wrinkles's dataset: 135 

### Technologies
Here are the library that used in this project
-	Tensorflow
-	Segmentation Models 
-	NumPy 
-	Pillow 
-	OpenCV 
-	Keras 

### Workflow Stages
1. Download and collect your own dataset from google images
2. Crop and Resize your input images dataset to 256x256, you can use [our notebook](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Data%20Preparation%20-%20Crop%20and%20Resize%20Face%20Image.ipynb).
3. Label your input images dataset using ImageJ. You can download ImageJ in [here](https://imagej.nih.gov/ij/download.html)
5. Stack your input images dataset using ImageJ software
6. Train the model, for training we use UNet architecture and ResNEt34 as our backbone (encoder). After that, save and export the existing model to .h5 file format. These are the notebook file that we work on:
   - [Acne Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Acne%20Skin%20Segmentation/Acne%20Segmentation%20-%20ResUnet.ipynb)
   - [Eye Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Eye%20Segmentation/Eye%20Segmentation%20-%20ResUnet.ipynb)
   - [Face Skin Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Face%20Skin%20Segmentation/Face%20Skin%20Segmentation%20-%20ResUnet.ipynb)
   - [Flek Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Flek%20Segmentation/Flek%20Segmentation%20-%20ResUnet.ipynb)
   - [Panda Eyes Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Panda%20Eyes%20Segmentation/Panda%20Eyes%20Segmentation%20-%20ResUnet.ipynb)
   - [Wrinkles Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Wrinkles%20Segmentation/Wrinkles%20Segmentation%20-%20ResUnet.ipynb.ipynb)
