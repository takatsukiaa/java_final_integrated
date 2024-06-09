from flask import Flask, render_template, request

app = Flask(__name__, template_folder='templates')

@app.route('/')
def home():
    return render_template('index.html')

if __name__ == '__main__':
    app.run(debug=True,host='0.0.0.0',port=3000)