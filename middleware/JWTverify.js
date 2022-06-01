const jwt = require('jsonwebtoken');
require('dotenv').config();

function authenticateToken (req, res, next) {
    const authHeader = req.headers['authorization']
    if(authHeader === null)
    {
        stats=401;
        error='Unauthorized';
        throw err;
    }
    try{
        const token = authHeader.split(' ')[1]
        const tokenUser = jwt.verify(token, process.env.ACCESS_TOKEN_SECRET)
        req.user = tokenUser
        next()
    }
    catch(err){
        console.log(err);
        res.status(400).send('Invalid Token');
    }
}
module.exports = authenticateToken