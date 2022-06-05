const { verify } = require('jsonwebtoken');
const IngredController = require('../controller/IngredientController');
const verifyToken = require('../middleware/JWTverify');

const router = require('express').Router();

router.post('/insertMaterial', IngredController.insertIngred);
router.post('/getAllRawMaterial', verifyToken, IngredController.getIngred);
router.post('/getRawMaterialByName', verifyToken, IngredController.getIngredByName);

module.exports = router;