const { verify } = require('jsonwebtoken');
const IngredController = require('../controller/IngredientController');
const verifyToken = require('../middleware/JWTverify');

const router = require('express').Router();

router.post('/InsertIngredients', IngredController.insertIngred);
router.get('/getAllIngred', verifyToken, IngredController.getIngred);
router.get('/getIngredByName', verifyToken, IngredController.getIngredByName);

module.exports = router;