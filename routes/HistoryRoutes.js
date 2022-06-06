const { verify } = require('jsonwebtoken');
const HistoryController = require('../controller/HistoryController');
const verifyToken = require('../middleware/JWTverify');

const router = require('express').Router();

router.post('/insertHistory', verifyToken, HistoryController.insertHistory);
router.post('/getHistoryByUsername', verifyToken, HistoryController.getHistoryByUsername);
router.post('/getHistoryByDate', verifyToken, HistoryController.getHistoryByDate);

module.exports = router;