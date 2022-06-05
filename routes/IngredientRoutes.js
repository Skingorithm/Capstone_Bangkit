const { verify } = require('jsonwebtoken');
const IngredController = require('../controller/IngredientController');
const verifyToken = require('../middleware/JWTverify');

const router = require('express').Router();

router.post('/insertMaterial', IngredController.insertIngred);
router.get('/getAllRawMaterial', verifyToken, IngredController.getIngred);
router.get('/getRawMaterialByName', verifyToken, IngredController.getIngredByName);

module.exports = router;