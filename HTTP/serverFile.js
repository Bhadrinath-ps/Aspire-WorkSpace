var express = require('express');
var session = require('express-session');
var cookie = require('cookie-parser');

const server = express();
const port = 5000;

server.use(cookie());

server.use(session({ secret: 'secretauth9090', resave: false, saveUninitialized: true }));

server.get('/statusset', (req, res) => {
    res.status(201).send("This is a custom status code response (201 Created)")
})

server.get('/allheader', (req, res) => {
    res.json(req.headers);
})

server.get('/get-session', (req, res) => {
    const user = req.session.userId;
    if (user) {
        res.send("Session attribute stored is " + user);
    } else {
        res.send("No session is set as of now");
    }
})

server.get('/set-session', (req, res) => {
    req.session.userId = req.query.username;
    res.send("Session attribute userId is to the Name")
})

server.all('/req-method', (req, res) => {
    switch (req.method) {
        case 'GET':
            res.send("Client has used GET method");
            break;
        case 'POST':
            res.send("Client has used POST method");
            break;
        case 'PUT':
            res.send("Client has used PUT method");
            break;
        default:
            res.send("Unsupported method of request");
    }
})

server.get('/set-cookie', (req, res) => {
    res.cookie("user", "Bhadrinath", { maxAge: 3600 * 1000, httpOnly: true });
    res.send("Cookie with user key is sent.")
})

server.get('/get-cookie', (req, res) => {
    const user = req.cookies.user;
    if (user) {
        res.send('Cookie User: ' + user)
    }
    else {
        res.send("No Cookies Set Yet.")
    }
})

server.listen(port, () => {
    console.log("Express server started on port: " + port);
});