from calendar import c
from cgitb import reset
from distutils.util import execute
from pickletools import read_uint1
from re import U
from flask import Flask, jsonify
import json
import sqlite3
import requests

connection = sqlite3.connect('game.db',check_same_thread=False)
cursor = connection.cursor()


# table = """ CREATE TABLE user (
#             username VARCHAR(255) NOT NULL,
#             token VARCHAR(355) NOT NULL,
#             online int
#         ); """

# cursor.execute(table) 

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello World'

@app.route('/register/<uname>/<token>')
def reg(uname,token):
    for i in cursor.execute(f"SELECT username FROM user").fetchall():
        if i[0]==uname:
            cursor.execute(f"INSERT INTO user VALUES ('{uname}','{token}',0)")
            connection.commit()
            return 'done'
    return 'uname already registered'

@app.route('/updateAll')
def updateAll():
    cursor.execute("UPDATE user SET online=1 WHERE online=0")
    connection.commit()
    return "updated all"

@app.route('/update/<uname>/<token>')
def update(uname,token):
    for i in cursor.execute(f"SELECT username FROM user").fetchall():
        if i[0]==uname:
            cursor.execute(f"UPDATE user SET token='{token}' WHERE username='{uname}'")
            connection.commit()
            return "updated"
    return 'uname not found'

@app.route('/updateStatus/<uname>/<bit>')
def change(uname,bit):
    for i in cursor.execute(f"SELECT username FROM user").fetchall():
        if i[0]==uname:
            cursor.execute(f"UPDATE user SET online={bit} WHERE username='{uname}'")
            connection.commit()
            return "updated"
    return 'uname not found'
    
@app.route('/find')
def find():
    cursor.execute("SELECT username,token FROM user WHERE online=1")
    result=cursor.fetchall()
    if(len(result)>1):
        
        cursor.execute(f"UPDATE user SET online=0 WHERE username='{result[0][0]}'")
        cursor.execute(f"UPDATE user SET online=0 WHERE username='{result[1][0]}'")
        connection.commit()
        send(result[0][0],result[0][1],result[1][1],"1")
        send(result[1][0],result[1][1],result[0][1],"2")
        
        return "game created"
    return 'no users online'

@app.route('/delete')
def delete():
    cursor.execute("DELETE FROM user")
    return "deleted"

@app.route('/all')
def all():
    cursor.execute("SELECT * FROM user")
    print(cursor.fetchall())
    return "done"

def send(uname,fcm1,fcm2,priority):
    print(f"set {fcm1} with {fcm2}")
    url="https://fcm.googleapis.com/fcm/send"
    body={
        "to":fcm2,
        "data":{
            "text":uname+" "+fcm1+" "+priority,
            "title":"user details"
        },
        "notification":{
            "title":"game has created"
        },
        "priority":"high",
        "content_available":True
    }
    headers = {"Content-Type":"application/json",
        "Authorization": "key=AAAA80YscHo:APA91bH3eRx1cx8g54LtOrTkNzAXxiULNNUz7llT5_S9_XXLTQPSBxwkwlGojPnamB2XSE_8wkezbWWnfPE3NnhgPsjEz29QH5lMyvQIX49lIL_3ihv-UlJc1GnFdqNUnaeSQpPdkhCD"}
    requests.post(url, data=json.dumps(body), headers=headers)

if __name__ == '__main__':
    app.run(port=5000)
