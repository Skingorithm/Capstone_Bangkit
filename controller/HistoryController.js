const db = require('../model');
require('dotenv').config();
const History = db.History;
const User = db.User;

let stats = 500;
let error = 'Internal Server Error';


const insertHistory = async (req, res) => {
    try {
        const { Username,
        ScanDate,
        FaceShape,
        Photo,
        Jerawat,
        Kerutan,
        FlekHitam,
        MataPanda,
        Total } = req.body;
        const validateUser = await User.findOne({where: {Username:req.body.Username}})
        if(!validateUser)
        {
            error='User Tidak Ditemukan';
            stats=200;
        }
        const newHistory = new History ({
            Username,
            ScanDate,
            FaceShape,
            Photo,
            Jerawat,
            Kerutan,
            FlekHitam,
            MataPanda,
            Total
        });
        await newHistory.save();
        res.json({error:"false",status: "success", message:"success insert data"});
    }
    catch(err){
        console.log(err);
        console.log(error);
        console.error(error);
        res.status(stats);
        res.send({status:"error", message: error})
    }
}

const getHistoryByUsername = async (req, res) => {
    try{
        const getAllHistory = await History.findAll({where:{Username:req.body.Username}})
        if(!getAllHistory)
        {
            error='Data tidak ditemukan';
            stats=200;
        }
        res.json({message:"success", datalistset: getAllHistory})
    }
    catch(err){
        console.log(err);
        console.log(error);
        console.error(error);
        res.status(stats);
        res.send({status:"error", message: error})
    }
}

const getHistoryByDate = async (req, res) => {
    try{
        const getByDate = await History.findAll({where: {ScanDate: req.body.Date, Username:req.body.Username}})
        if(getByDate == null)
        {
            stats=200;
            error='No Data found'
        }
        const byDateResult = getByDate;
        // byNameResult(getByName);
        res.json({status:"success", datalistset: byDateResult})
    }
    catch(err){
        console.log(err);
        console.log(error);
        console.error(error);
        res.status(stats);
        res.send({status:"error", message: error})
    }
}

module.exports = {
    insertHistory,
    getHistoryByUsername,
    getHistoryByDate
}