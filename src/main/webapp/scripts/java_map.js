/**
 * Created by ramroop on 5/3/17.
 */
//Variables used in game

//Create canvas to display the game,'game' will refer to this canvas element
var game = new Phaser.Game(1000,600,Phaser.CANVAS,'gameDiv');
var player;


var map;
var basics;
var conditionals;
var loops;
var final;
var basicsZone;
var conditionalsZone;
var loopsZone;
var finalZone;
var alertShown = false; // For now, keep alert from looping infinitely

var mainstate = {
    preload:function () {

        //Load in JsonFile for the tileMap + needed tilesets to render the map
        game.load.tilemap("ItsTheMap","../images/java_map.json",null,Phaser.Tilemap.TILED_JSON);
        game.load.image("medieval_tileset","../images/medieval_tileset.png");


        //get users sprite and load appropriate sprite sheet
        var spritesheet = document.getElementById('avtr').getAttribute('src');
        spritesheet = spritesheet.slice(3,spritesheet.length-4);
        spritesheet = spritesheet.concat('_spritesheet.png');
        game.load.spritesheet('sprite', spritesheet, 32,32);
    },

    create:function () {
        //load map & assests for map
        map = game.add.tilemap("ItsTheMap",32,32,30,30);
        map.addTilesetImage("medieval_tileset","medieval_tileset");
        map.createLayer("GroundLayer").resizeWorld();
        map.createLayer("HouseLayer").resizeWorld();
        map.createLayer("MiscLayer")


        //get player
        player = new Phaser.Sprite(game,0,0,"sprite");
        game.world.addAt(player,1);
        game.camera.follow(player);


        //get objects for the tasks

        //start position
        var start =map.objects["tasks"][4];

        //basics
        basics = map.objects["tasks"][0];

        //final
        final = map.objects["tasks"][1];

        //conditionals
        conditionals= map.objects["tasks"][2];

        //loops
        loops = map.objects["tasks"][3]


        //define basics zone
         basicsZone = new Phaser.Rectangle(basics.x,basics.y,basics.width,basics.height);

        //define conditionals zone
        conditionalsZone = new Phaser.Rectangle(conditionals.x,conditionals.y,conditionals.width,conditionals.height);


        //define loops zone
        loopsZone = new Phaser.Rectangle(loops.x,loops.y,loops.width,loops.height);

        //define final zone
        finalZone = new Phaser.Rectangle(final.x,final.y,final.width,final.height);

        // //set player to starting point
        player.position.set(start.x,start.y);






        //Animations for player up corresponds to frame 0,1 in spritesheet,etc.
        player.animations.add('up', [0, 1], 10, true);
        player.animations.add('down', [2, 3], 10, true);
        player.animations.add('left', [4,5], 10, true);
        player.animations.add('right', [6,7], 10, true);

    },

    update:function () {

        if (game.input.keyboard.isDown(Phaser.Keyboard.LEFT))
        {
            //  Move to the left
            player.x -=4;

            player.animations.play('left');
        }
        else if (game.input.keyboard.isDown(Phaser.Keyboard.RIGHT))
        {
            //  Move to the right
            player.x += 4;

            player.animations.play('right');
        }
        else if (game.input.keyboard.isDown(Phaser.Keyboard.UP))
        {
            player.y -=4;
            player.animations.play('up');
        }
        else if(game.input.keyboard.isDown(Phaser.Keyboard.DOWN))
        {
            player.y +=4;
            player.animations.play('down');
        }
        else
        {
            //  Stand still
            player.animations.stop();

            player.frame = 3;
        }


         //Check if in basics
        if (basicsZone.contains(player.x+player.width/2,player.y+player.height/2)) {
            //Basics name for java
            post('/task/getJavaTasksPage', {numBuilding: '0'});
        }
         //check if in conditionals
        if (conditionalsZone.contains(player.x+player.width/2,player.y+player.height/2)) {
            post('/task/getJavaTasksPage', {numBuilding: '1'});
        }
        //check if in loops
        if (loopsZone.contains(player.x+player.width/2,player.y+player.height/2)) {
            post('/task/getJavaTasksPage', {numBuilding: '2'});
        }
        //check if in final
        if (finalZone.contains(player.x+player.width/2,player.y+player.height/2) && !alertShown) {
            console.log("final");
            console.log(final.name);
            alert("Sorry,you must unlock this building");
            alertShown = true;
        }


    }
}

game.state.add('mainstate',mainstate);
game.state.start('mainstate');

function post(path, params, method) {
    method = method || "post"; // Set method to post by default if not specified.

    // The rest of this code assumes you are not using a library.
    // It can be made less wordy if you use one.
    var form = document.createElement("form");
    form.setAttribute("method", method);
    form.setAttribute("action", path);

    for(var key in params) {
        if(params.hasOwnProperty(key)) {
            var hiddenField = document.createElement("input");
            hiddenField.setAttribute("type", "hidden");
            hiddenField.setAttribute("name", key);
            hiddenField.setAttribute("value", params[key]);

            form.appendChild(hiddenField);
         }
    }

    document.body.appendChild(form);
    form.submit();
}
