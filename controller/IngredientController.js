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
        console.error(err);
        res.status(stats);
        res.json({status: "error", message: error})
    }
}

const getIngred = async (req, res) => {
    try{
        const getAllIngred = await Ingredient.findAll({})
        res.json({error:"false", message:"success", datalistset: getAllIngred})
    }
    catch(err){
        console.error(err.message);
        res.status(500).send("Server Error!");
    }
}

const getIngredByName = async (req, res) => {
    try{
        const { IngredName } = req.body;
        const getByName = await Ingredient.findOne({where: {IngredName:req.body.IngredName}})
        const byNameResult = getByName;
        // byNameResult(getByName);
        res.json({error:"false", message:"success", datalistset: byNameResult})
    }
    catch(err){
        console.error(err.message);
        res.status(500).send("Server Error!");
    }
}

module.exports = {
    insertIngred,
    getIngred,
    getIngredByName
}