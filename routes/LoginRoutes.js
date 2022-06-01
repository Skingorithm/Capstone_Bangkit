const LoginController = require('../controller/LoginController');
const verifyToken = require('../middleware/JWTverify');

const router = require('express').Router();

router.post('/userLogin', LoginController.userLogin);
router.post('/refreshToken', LoginController.tokenRefresh);
router.delete('/userLogout', LoginController.userLogout);

module.exports = router;