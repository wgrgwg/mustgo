from flask import Flask, request, jsonify
import tensorflow as tf
import pandas as pd
from tensorflow.keras.layers import Dense, BatchNormalization
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import LabelEncoder
import joblib
import logging
import json
import numpy as np

app = Flask(__name__)
logger = logging.getLogger(__name__)
#model = joblib.load("C:/Users/admin/Documents/CNU/Internship/Panmnesia/demo/src/main/resources/python/saved.pkl")
model = tf.keras.models.load_model("C:/Users/admin/Documents/CNU/Internship/Panmnesia/demo/src/main/resources/python/tfmodel.h5")


# Define the Swagger specification

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json
    
    # JSON 데이터를 Python 사전(Dictionary)으로 변환
    data_dict = {
        "age": int(data['age']),
        "gender": 0 if data['gender'] == 'MALE' else 1,
        "currentSeason": int(data['currentSeason']),
        "isSpicy": int(data['isSpicy']),
        "isRice": int(data['isRice']),
        "isDinner": int(data['isDinner']),
        "priceFavor": int(data['priceFavor'])
    }
    
    # age 값을 10 단위로 변환
    data_dict['age'] = (data_dict['age'] // 10) * 10

    # 데이터를 순서대로 배열에 저장
    input_data = [
        data_dict['age'],
        data_dict['gender'],
        data_dict['currentSeason'],
        data_dict['isSpicy'],
        data_dict['isRice'],
        data_dict['isDinner'],
        data_dict['priceFavor']
    ]

    x = np.array(input_data)  # 1차원 입력 데이터
    x = np.expand_dims(x, axis=0)  # (1, 7) 형태로 변환

    prediction = model.predict(x) # 모델 예측
    #predicttion = input_data

    class_index = int(np.argmax(prediction))

    return jsonify(class_index)

if __name__ == '__main__':
    app.run(port=5000)
