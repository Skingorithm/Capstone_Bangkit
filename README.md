# Skin Gorithm - Machine Learning

## 1. Batik Making Technique Classification

### INTRODUCTION


### DATASET
We collect datasets manually from google images. The distribution as follows :
- Face skin's dataset 
- Acne's dataset 
- Black Spot's dataset 
- Eye's dataset 
- Dark Circle's dataset 
- Wrinkle's dataset


### TECHNOLOGIES
This project is built using Deep Learning Technology with Convolutional Neural Network algorithm and Transfer Learning. Here are the library that used in this project.
- Python 3.6.9
- TensorFlow 2.4.1
- NumPy 1.20.0
- Matplotlib 3.4.2

### LAUNCH
Inside batik-technique-classification folder, there are some files:
- [batik-technique-classification_(without_transfer_learning).ipynb](https://github.com/farrel25/bangkit-capstone-project/blob/machine-learning/batik-technique-classification/batik-technique-classification_(without_transfer_learning).ipynb)
- [batik-technique-classification_(using_mobilenet).ipynb](https://github.com/farrel25/bangkit-capstone-project/blob/machine-learning/batik-technique-classification/batik_technique_classification_(using_mobilenet).ipynb)
- [batik-technique-classification_(using_xception).ipynb](https://github.com/farrel25/bangkit-capstone-project/blob/machine-learning/batik-technique-classification/batik_technique_classification_(using_xception).ipynb)
- [test_model.ipynb](https://github.com/farrel25/bangkit-capstone-project/blob/machine-learning/batik-technique-classification/test_model.ipynb)



## 2. Face Shape Classification
Consisting 5 motifs batik to classify, including :
1. Ceplok
2. Kawung
3. Parang
4. Megamendung 
5. Sidomukti

### DATASET
After we collected dataset manually from various sources, we got total 3.118 datasets. The distribution as follows :
- Ceplok's dataset : 708
- Kawung's dataset : 661
- Parang's dataset : 922
- Megamendung's dataset : 560
- Sidomukti's dataset : 267

### TRANSFER LEARNING
We use Convolutional Neural Network and the base model from the InceptionV3 pre-trained model developed at Google on Imagenet Dataset

### SUPPORTED BY 
Tensorflow : 2.5.0

### LAUNCH
Inside batik-motif-classification folder, we use fix model : [colab_image_classification_inceptionv3.ipynb](https://github.com/farrel25/naratik/blob/machine-learning/batik-motif-classification/colab_image_classification_inceptionv3.ipynb)
