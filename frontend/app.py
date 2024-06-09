from flask import Flask, render_template, request, redirect

app = Flask(__name__, template_folder='templates')

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/<path:url_hash>')
def redirect_anystring(url_hash):
    return redirect(f'/api/{url_hash}')

if __name__ == '__main__':
    app.run(debug=True,host='0.0.0.0',port=3000)
