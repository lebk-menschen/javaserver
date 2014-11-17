// Express Server
var express   = require('express');
var app       = express();
var port      = process.env.port || 8080;
var _         = require('lodash');


// configuration =================================================

// Static files
app.use(express.static(__dirname + '/../public'));

app.get('/api/create', function (req, res) {

  var ret = {
    token_user: 'USER_' + new Date().getTime(),
    token_opponent: 'OPPONENT_' + new Date().getTime()
  };

  res.json(ret);
});

app.get('/api/match', function (req, res) {
  var ret = {
    fields: {
      player: [],
      opponent: []
    }
  };

  res.json(ret);
});

app.listen(port);
console.log('Dummy Server listening on port ' + port);
