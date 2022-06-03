const db = require('../model');
require('dotenv').config();
const Ingredient = db.Ingredient;

let stats = 500;
let error = 'Internal Server Error';


const insertIngred = async (req, res) => {
    try {
        const { IngredName, IngredFunction, IngredEffect } = req.body;

        const validateIngred = await Ingredient.findOne({where: {IngredName:req.body.IngredName}})
        if(validateIngred != null){
            error = 'Ingredient sudah terdaftar! Silahkan masukkan Ingredient lain';
            stats = 200;
            throw err;
        }

        const newIngred = new Ingredient ({
            IngredName, 
            IngredFunction, 
            IngredEffect
        });
        await newIngred.save();
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

const getIngred = async (req, res) => {
    try{
        const getAllIngred = await Ingredient.findAll({})
        res.json({error:"false", message:"success", datalistset: getAllIngred})
    }
    catch(err){
        console.log(err);
        console.log(error);
        console.error(error);
        res.status(stats);
        res.send({status:"error", message: error})
    }
}

const getIngredByName = async (req, res) => {
    try{
        const { IngredName } = req.body;
        const getByName = await Ingredient.findOne({where: {IngredName:req.body.IngredName}})
        if(getByName == null)
        {
            stats=200;
            error='No ingredient name found'
        }
        const byNameResult = getByName;
        // byNameResult(getByName);
        res.json({error:"false", message:"success", datalistset: byNameResult})
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
    insertIngred,
    getIngred,
    getIngredByName
}