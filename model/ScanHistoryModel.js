const Seq = require('sequelize');
module.exports = (sequelize) => {
    const History = sequelize.define(
        "ScanHistory",
        {
            Username:{type: Seq.STRING, allowNull: false},
            ScanDate:{type: Seq.DATEONLY, allowNull: false},
            FaceShape:{type: Seq.STRING, allowNull: true},
            Photo: {type: Seq.STRING, allowNull: false},
            Jerawat: {type: Seq.INTEGER, allowNull: false},
            Kerutan: {type: Seq.INTEGER, allowNull: false},
            FlekHitam: {type: Seq.INTEGER, allowNull: false},
            MataPanda: {type: Seq.INTEGER, allowNull: false},
            Total: {type: Seq.INTEGER, allowNull: false}
        },
        {
            freezeTableName: true,
            timestamp: false
        }
    );
    return History;
}