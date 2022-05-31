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
                Date,
                fifteenBefore,
                thirtyBefore,
                repeatAlarm,
                Username
            } = req.body;
        const checkUser = await User.findOne({where: {Username:req.body.Username}})
        if(checkUser != null)
        {
            const newAlarm = new Notify ({
                RoutinityName, 
                NotifyHour,
                Date,
                fifteenBefore,
                thirtyBefore,
                repeatAlarm,
                Username
            })
            await newAlarm.save();
            res.send({status: 'success', message:'Berhasil Menambahkan Alarm'});
        }
        else{
            stats=404;
            error="User not Found";
            throw err;
        }
    }
    catch(err){
        console.error(err);
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
        res.json(getAlarmByUsername)
    }
    catch(err){
        console.error(err);
        res.json(error);
    }
}

module.exports = {
    insertAlarm,
    getAlarmbyUsername
}