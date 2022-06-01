# Skin Gorithm - Machine Learning Notebook

## 1. Facial Skin Status Analyze

### INTRODUCTION
Recently, the trend of skincare products has increased rapidly. In 2021 the demand for skincare in Indonesia is 70% higher than the previous year. This is due to many things, starting with the higher demand for skincare products, various skin problems, and also because of the rampant influence from skincare influencers that encourage people to be more aware of their skin health. Along with the increasing demand and usage of skincare products, there are still a lot of people who lack knowledge about the skincare things, a lot of people are still confused about how to choose the best skincare for themselves, and they are still not pretty sure is the skincare they using is effective for their skin or not. 

Therefore, this project is built an app to analysis of facial skin status, such as acne, dark circles, flek and wrinkles using Deep Learning Technology. In addition, our app also provides features ingredient analyze to help users can understand more about the skincare function, pros, and cons based on the ingredients, alarm feature to remind the users of their skincare daily usage, and the most important thing they can see their progress in their skincare journey!

### DATASET
We collect datasets manually from google images. The distribution as follows :
- Acne's dataset: 40 
- Dark circle's dataset: 50 
- Eye's dataset: 60
- Face skin's dataset: 80
- Flek's dataset: 43
- Wrinkles's dataset: 135

### WORKFLOW STAGES
1. Cropping your face images dataset, you can use [our notebook](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Cropping%20Face%20Images.ipynb) to cropping your face images dataset 
2. Labelling your images dataset using ImageJ. You can download ImageJ in [here](https://imagej.nih.gov/ij/download.html)
3. Resize your input images to 256x256, you can use [our notebook](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Resize%20Image%20to%20256x256.ipynb) to resize your images
4. Stack your input images using ImageJ software
5. Training the model, we using ResUNet architecture as our baseline. But, you can start training your own model immediately by modifying the Model Section or load your own model. There are some files of our notebook:
   - [Acne Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Acne%20Skin%20Segmentation/Acne%20Segmentation%20-%20ResUnet.ipynb)
   - [Dark Circle Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Dark%20Circle%20Segmentation/Dark%20Circle%20Segmentation%20-%20ResUnet.ipynb)
   - [Eye Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Eye%20Segmentation/Eye%20Segmentation%20-%20ResUnet.ipynb)
   - [Face Skin Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Face%20Skin%20Segmentation/Face%20Skin%20Segmentation%20-%20ResUnet.ipynb)
   - [Flek Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Flek%20Segmentation/Flek%20Segmentation%20-%20ResUnet.ipynb)
   - [Wrinkles Segmentation - ResUnet.ipynb](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Wrinkles%20Segmentation/Wrinkles%20Segmentation%20-%20ResUnet.ipynb)
6. Load the model and convert model to tflite format, you can use [our notebook](https://github.com/Skingorithm/Capstone_Bangkit/blob/Machine-Learning/Load%20and%20Convert%20to%20TFLite.ipynb) to convert your model


## 2. Face Shape Classification

