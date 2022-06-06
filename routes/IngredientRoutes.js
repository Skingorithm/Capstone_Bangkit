const { verify } = require('jsonwebtoken');
const IngredController = require('../controller/IngredientController');
const verifyToken = require('../middleware/JWTverify');

const router = require('express').Router();

router.post('/InsertIngredients', IngredController.insertIngred);
router.get('/getAllIngredient', verifyToken, IngredController.getIngred);
router.post('/getIngredByName', verifyToken, IngredController.getIngredByName);

module.exports = router;