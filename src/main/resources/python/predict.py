from flask import Flask, request, jsonify
# import your_model # 여기서 your_model은 실제 모델 파일을 의미합니다.

app = Flask(__name__)
# model = your_model.load_model() # 모델 로딩

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json
    # prediction = model.predict(data) # 모델 예측
    prediction = data
    return jsonify(prediction)

if __name__ == '__main__':
    app.run(port=5000)
