const { get } = require('express/lib/response');
const db = require('../model');
require('dotenv').config();
const Notify = db.Notify;
const User = db.User;

let stats = 500;
let error = 'Internal Server Error';


const insertAlarm = async (req, res) => {
    try {
        const { RoutinityName, 
                NotifyHour,
                AlarmDate,
                fifteenBefore,
                thirtyBefore,
                repeatAlarm,
                Username
            } = req.body;
            let Tanggal = new Date(`${req.body.AlarmDate}`);
        const checkUser = await User.findOne({where: {Username:req.body.Username}})
        if(checkUser != null)
        {
            if(req.body.repeatAlarm == 0)
            {
                const newAlarm = new Notify ({
                    RoutinityName, 
                    NotifyHour,
                    AlarmDate,
                    fifteenBefore,
                    thirtyBefore,
                    repeatAlarm,
                    Username
                })
                await newAlarm.save();
                res.send({status: 'success', message:'Berhasil Menambahkan Alarm'});
            }
            else if(req.body.repeatAlarm == 1)
            {
                for(let i = 0; i < 4; i++)
                {
                    Tanggal.setDate(Tanggal.getDate() + 7);
                    console.log(Tanggal);
                    const newAlarm = new Notify ({
                        RoutinityName, 
                        NotifyHour,
                        AlarmDate: Tanggal,
                        fifteenBefore,
                        thirtyBefore,
                        repeatAlarm,
                        Username
                    })
                    await newAlarm.save();
                }
                res.send({status: 'success', message:'Berhasil Menambahkan Alarm'});
            }
            else if(req.body.repeatAlarm == 2)
            {
                const newAlarm = new Notify ({
                    RoutinityName, 
                    NotifyHour,
                    AlarmDate,
                    fifteenBefore,
                    thirtyBefore,
                    repeatAlarm,
                    Username
                })
                await newAlarm.save();
                res.send({status: 'success', message:'Berhasil Menambahkan Alarm'});
            }
        }
        else{
            stats=200;
            error="User not Found";
            throw err;
        }
    }
    catch(err){
        console.log(err);
        console.log(error);
        console.error(error);
        res.status(stats);
        res.send({status:"error", message: error})
    }
}

const getAlarmbyUsername = async (req, res) => {
    try{
        const {Username, RoutinityName} = req.body;
        const getAlarmByUsername = await Notify.findAll({where: {Username: req.body.Username}})
        if(!getAlarmByUsername)
        {
            stats=404;
            error='No Alarm Found';
            throw err;
        }
        res.json({status:'success', datalistset: getAlarmByUsername});
    }
    catch(err){
        console.log(err);
        console.log(error);
        console.error(error);
        res.status(stats);
        res.send({status:"error", message: error})
    }
}

const getByDate = async (req, res) => {
    try{
        const {Username, Date} = req.body;
        const getAlarmByDate = await Notify.findAll({where:{AlarmDate: req.body.Date, Username:req.body.Username}})
        if(!getAlarmByDate)
        {
            stats=404;
            error='No Alarm Found';
            throw err;
        }
        res.json({status:'success', datalistset: getAlarmByDate});
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
    insertAlarm,
    getAlarmbyUsername,
    getByDate
}