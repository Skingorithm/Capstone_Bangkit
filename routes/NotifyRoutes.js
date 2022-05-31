const NotifyController = require('../controller/NotifyController');
const verifyToken = require('../middleware/JWTverify');

const router = require('express').Router();

router.post('/insertAlarm', verifyToken, NotifyController.insertAlarm);
router.get('/getAlarmByUsername', verifyToken, NotifyController.getAlarmbyUsername);

module.exports = router;