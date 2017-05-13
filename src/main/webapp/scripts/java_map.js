//Variables used in game

//Create canvas to display the game,'game' will refer to this canvas element
var game = new Phaser.Game(800,600,Phaser.CANVAS,'gameDiv');
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
var alertShown0 = false; // For now, keep alert from looping infinitely
var alertShown1 = false; // For now, keep alert from looping infinitely
var alertShown2 = false; // For now, keep alert from looping infinitely
var alertShown3 = false; // For now, keep alert from looping infinitely


var mainstate = {
    preload:function () {

        //Load in JsonFile for the tileMap + needed tilesets to render the map
        game.load.tilemap("ItsTheMap","../images/tilesets/java_map.json",null,Phaser.Tilemap.TILED_JSON);
        game.load.image("medieval_terrain","../images/tilesets/medieval_terrain.png");
        game.load.image("medieval_houses","../images/tilesets/medieval_houses.png");
        game.load.image("medieval_animals","../images/tilesets/medieval_animals.png");
        game.load.image("TileC","../images/tilesets/TileC.png");
        game.load.image("npc_spritesheet","../images/tilesets/npc_spritesheet.png");


        //get users sprite and load appropriate sprite sheet
        var spritesheet = document.getElementById('avtr').getAttribute('src');
        spritesheet = spritesheet.slice(3,spritesheet.length-4);
        spritesheet = spritesheet.concat('_spritesheet.png');
        game.load.spritesheet('sprite', spritesheet, 32,32);
    },

    create:function () {
        //load map & assests for map
        map = game.add.tilemap("ItsTheMap",32,32,40,40);
        map.addTilesetImage("medieval_terrain","medieval_terrain");
        map.addTilesetImage("medieval_houses","medieval_houses");
        map.addTilesetImage("medieval_animals","medieval_animals");
        map.addTilesetImage("TileC","TileC");
        map.addTilesetImage("npc_spritesheet","npc_spritesheet");

        //create the base layer ,these are the floors walls and anything else we want behind any sprites
        map.createLayer("Background").resizeWorld();
        map.createLayer("Midground");
        map.createLayer("Foreground");
        layer = map.createLayer("Collision")
        layer.visible = false;



        //get objects for the tasks

        //start position
        var start =map.objects["tasks"][0];

        //basics
        basics = map.objects["tasks"][1];

        //final
        conditionals = map.objects["tasks"][2];

        //conditionals
        loops = map.objects["tasks"][3];

        //loops
        final = map.objects["tasks"][4];


        //define basics zone
        basicsZone = new Phaser.Rectangle(basics.x,basics.y,basics.width,basics.height);

        //define conditionals zone
        conditionalsZone = new Phaser.Rectangle(conditionals.x,conditionals.y,conditionals.width,conditionals.height);


        //define loops zone
        loopsZone = new Phaser.Rectangle(loops.x,loops.y,loops.width,loops.height);

        //define final zone
        finalZone = new Phaser.Rectangle(final.x,final.y,final.width,final.height);

        //get player
        player = new Phaser.Sprite(game,start.x,start.y,"sprite");
        //Apply physics to our game sprite

        game.world.addAt(player,2);
        game.camera.follow(player);


        //Handle collisions
        game.physics.startSystem(Phaser.Physics.P2JS);
        map.setCollision(3812,true,"Collision");
        game.physics.p2.convertTilemap(map, "Collision");
        game.physics.p2.enable(player);
        player.body.fixedRotation = true;




        //Animations for player up corresponds to frame 0,1 in spritesheet,etc.
        player.animations.add('up', [0, 1], 10, true);
        player.animations.add('down', [2, 3], 10, true);
        player.animations.add('left', [4,5], 10, true);
        player.animations.add('right', [6,7], 10, true);

    },

    update:function () {

        var speed=100;
        if (game.input.keyboard.isDown(Phaser.Keyboard.LEFT))
        {
            //  Move to the left
            player.body.velocity.x = -speed;

            player.animations.play('left');
        }
        else if (game.input.keyboard.isDown(Phaser.Keyboard.RIGHT))
        {
            //  Move to the right
            player.body.velocity.x = speed;

            player.animations.play('right');
        }
        else if (game.input.keyboard.isDown(Phaser.Keyboard.UP))
        {
            player.body.velocity.y = -speed;
            player.animations.play('up');
        }
        else if(game.input.keyboard.isDown(Phaser.Keyboard.DOWN))
        {
            player.body.velocity.y = speed;
            player.animations.play('down');
        }
        else
        {
            //  Stand still
            player.body.velocity.y = 0;
            player.body.velocity.x = 0;
            player.frame = 3;
        }


        //Check if in basics
        if (basicsZone.contains(player.x+player.width/2,player.y+player.height/2)) {
            post('/tasks/task', {languageCode: '3', numBuilding: '0', floorNum: '-1'});
        }
        //check if in conditionals
        if (conditionalsZone.contains(player.x+player.width/2,player.y+player.height/2)) {
            post('/tasks/task', {languageCode: '3', numBuilding: '1', floorNum: '-1'});
        }
        //check if in loops
        if (loopsZone.contains(player.x+player.width/2,player.y+player.height/2)) {
            post('/tasks/task', {languageCode: '3', numBuilding: '2', floorNum: '-1'});
        }
        //check if in final
        if (finalZone.contains(player.x+player.width/2,player.y+player.height/2)
            && !alertShown) {
            console.log("final");
            console.log(final.name);
            alert("Can't do your final project without the core skills. " +
                "Complete the previous buildings and come back!");
            alertShown = true;
        }

    }
}

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

game.state.add('mainstate',mainstate);
game.state.start('mainstate');
