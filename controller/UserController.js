const db = require('../model');
require('dotenv').config();
const User = db.User;
const bcrypt = require('bcrypt');
const jwt = require('jsonwebtoken');

let error = 'Internal Server Error';
let stats = 500;
let uName = '';
const userRegister = async (req, res) => {
    try {
        const {
            Username,
            Email,
            Password,
            FullName,
            ProfilePicture
        } = req.body;
        if(req.body.Username != req.body.Username.toLowerCase())
        {
            error = 'Username tidak boleh menggunakan huruf besar';
            stats = 200;
            throw err;
        }
        const getUser = await User.findOne({where: {Username:req.body.Username}})
        if(getUser != null)
        {
            error = 'Username sudah terdaftar, silahkan gunakan username lain';
            stats = 200;
            throw err;
        }
        const getEmail = await User.findOne({where: {Email:req.body.Email}})
        if(getEmail != null)
        {
            error = 'Email sudah digunakan oleh user lain, Silahkan gunakan email lain';
            stats = 200;
            throw err;
        }
        const saltRounds = 10;
        const hashedPassword = await bcrypt.hash(req.body.Password, saltRounds);
        console.log(hashedPassword);
        const newUser = new User ({
            Username,
            Email,
            Password: hashedPassword,
            FullName,
            ProfilePicture
        });
        await newUser.save();
        res.json({status:'Success', message:'Berhasil melakukan registrasi'});
    }
    catch(err){
        console.error(err);
        res.status(stats).send(error);
    }
}

const getAllUser = async (req, res) => {
    try{
        const getAllUser = await User.findAll({});
        res.json({status:'success', datalistset: getAllUser});
    }
    catch(err){
        console.error(err);
        res.status(500).send("Server Error!");
    }
}

const getByUsername = async (req, res) => {
    try{
        const { Username } = req.body;
        const getByUsername = await User.findOne({where: {Username:req.body.Username}})
        if(getByUsername == null)
        {
            error = 'User Tidak Ditemukan!';
            stats = 200;
            throw err;
        }
        res.json({status: 'success', datalistset: getByUsername});
    }
    catch(err){
        console.error(err);
        res.status(stats);
        res.send({status:"error", message: error})
    }
}

const updateUser = async (req, res) => {
    try{
        const {
            Username,
            Email,
            Password,
            FullName,
            ProfilePicture
        } = req.body;
        const getBefore = await User.findOne({where: {Username:req.body.Username}})
        if(getBefore == null)
        {
            stats=200;
            error='User tidak ditemukan';
            throw err;
        }
        else
        {
            uName = req.body.Username;
            let FNUpdate = getBefore.FullName;
            let PPUpdate = getBefore.ProfilePicture;
            if(req.body.ProfilePicture != null)
            {
                PPUpdate = req.body.ProfilePicture;
            }
            if(req.body.FullName != null)
            {
                FNUpdate = req.body.FullName;
            }
            const update = await User.update({
                Username: getBefore.Username,
                Email: getBefore.Email,
                Password: getBefore.Password,
                FullName: FNUpdate,
                ProfilePicture: PPUpdate
            }, {where: {Username: req.body.Username}})

            await update;
            
            res.json({status:'success', message:'Berhasil memperbarui data'});
        }
    }
    catch(err){
        console.error(err);
        res.status(stats);
        res.send({status:"error", message: error})
    }
}

const changePassword = async (req, res) => {
    try{
        const {Username, currentPassword, newPassword} = req.body;
        const getBefore = await User.findOne({where: {Username: req.body.Username}})
        if(!getBefore)
        {
            stats=200;
            error='User tidak ditemukan';
            throw err;
        }
        const hashedCompare = await bcrypt.compare(req.body.currentPassword, getBefore.Password)
        if(hashedCompare == false)
        {
            stats=200;
            error='Password sebelumya salah';
            throw err;
        }
        else
        {
            const saltRounds = 10;
            const hashedPassword = await bcrypt.hash(req.body.newPassword, saltRounds);
            const updatePassword = await User.update({
                Username: getBefore.Username,
                Email: getBefore.Email,
                Password: hashedPassword,
                FullName: getBefore.FullName,
                ProfilePicture: getBefore.ProfilePicture
            }, {where: {Username: req.body.Username}})

            await updatePassword;
            res.json({status:'success', message: 'Berhasil mengubah password'})
        }
    }
    catch(err){
        console.error(err);
        res.status(stats);
        res.send({status:"error", message: error})
    }
}

module.exports = {
    getAllUser,
    userRegister,
    getByUsername,
    updateUser,
    changePassword
}