const jwt = require('jsonwebtoken');
const db = require('../model');
const User = db.User;
const RT = db.RefreshToken;
const bcrypt = require('bcrypt');
require('dotenv').config();

let error = 'Internal Server Error';
let stats = 500;

const expireTime = '30m';

const userLogin = async (req, res) => {
    try {
        const { Username, Password } = req.body
        const userCheck = await User.findOne({where: {Username:req.body.Username}})
        if(userCheck == null)
        {
            stats = 200;
            error = "User Tidak Terdaftar!"
        }
        else 
        {
            const hashedCompare = await bcrypt.compare(req.body.Password, userCheck.Password)
            if(hashedCompare)
            {
                const accessToken = generateAccessToken(userCheck.Username)
                const refreshToken = jwt.sign(userCheck.Username, process.env.REFRESH_TOKEN_SECRET)
                console.log(refreshToken);
                const insertRT = new RT({
                    RTStore: refreshToken
                });
                await insertRT.save()
                res.json({message:'Berhasil Login', accessToken: accessToken, refreshToken: refreshToken, expiredIn: expireTime})
            }
            else
            {
                stats = 200;
                error = "Password Yang Anda Masukkan Salah!";
            }
        }
    }
    catch(err){
        console.error(err);
        res.status(stats).send(error)
    }
}
const tokenRefresh = async (req, res) => {
    try{
        const{Username, RefreshToken} = req.body
        if(req.body.RefreshToken == null) return res.sendStatus(401)
        var UN = req.body.Username
        const RTChecker = await RT.findOne({where: {RTStore:req.body.RefreshToken}})
        if(RTChecker == null)
        {
            stats=400;
            error='Invalid Refresh Token';
            throw err;
        }
        else{
            jwt.verify(RefreshToken, process.env.REFRESH_TOKEN_SECRET,(err, UN) => {
                if(err) return res.sendStatus(403)
                const newAccessToken = generateAccessToken(UN)
                res.json({ accessToken: newAccessToken, expiredIn: expireTime })
            })
        }
    }
    catch(err){
        console.error(err)
        res.status(stats).send(error)
    }
}

const userLogout = async (req, res) => {
    try{
        const { RefreshToken } = req.body
        const getRefreshToken = await RT.findOne({where: {RTStore: req.body.RefreshToken}})
        if(getRefreshToken == null)
        {
            stats=404;
            error='Invalid Refresh Token';
            throw err;
        }
        const deleteRT = await RT.destroy({where: {RTStore: req.body.RefreshToken}})
        await deleteRT;
        res.json({status: 'success', message: 'Berhasil Logout'})
    }
    catch(err){
        console.error(err);
        res.status(stats);
        res.send({status:"error", message: error})
    }
}

function generateAccessToken (user) {
    const generatedToken =  jwt.sign({user}, process.env.ACCESS_TOKEN_SECRET, { expiresIn: expireTime });
    console.log(generateAccessToken);
    return generatedToken;
}
module.exports = {userLogin, tokenRefresh, userLogout}