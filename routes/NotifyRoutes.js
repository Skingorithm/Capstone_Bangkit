const NotifyController = require('../controller/NotifyController');
const verifyToken = require('../middleware/JWTverify');

const router = require('express').Router();

router.post('/insertAlarm', verifyToken, NotifyController.insertAlarm);
router.post('/getAlarmByUsername', verifyToken, NotifyController.getAlarmbyUsername);
router.post('/getAlarmByDate', verifyToken, NotifyController.getByDate);
module.exports = router;