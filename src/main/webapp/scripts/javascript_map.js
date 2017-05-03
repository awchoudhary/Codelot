//Variables used in game

//Create canvas to display the game,'game' will refer to this canvas element
var game = new Phaser.Game(800,800,Phaser.CANVAS,'gameDiv');
var player;


var map;
var basics;
var conditonals;
var loops;
var final;
var alertShown = false; // For now, keep alert from looping infinitely


var mainstate = {
    preload:function () {

        //Load in JsonFile for the tileMap + needed tilesets to render the map
        game.load.tilemap("ItsTheMap","../images/javascript_map.json",null,Phaser.Tilemap.TILED_JSON);
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
        game.world.addAt(player,2);
        game.camera.follow(player);


        //get objects for the tasks

        //start position
        var start =map.objects["tasks"][0];

        //basics
        var build1 = map.objects["tasks"][1];

        //final
        var build2 = map.objects["tasks"][2];

        //conditionals
        var build3 = map.objects["tasks"][3];

        //loops
        var build4 = map.objects["tasks"][4]


        //define basics zone
        basics = new Phaser.Rectangle(build1.x,build1.y,build1.width,build1.height);

        //define conditionals zone
        conditonals = new Phaser.Rectangle(build3.x,build3.y,build3.width,build3.height);


        //define loops zone
        loops = new Phaser.Rectangle(build4.x,build4.y,build4.width,build4.height);

        //define final zone
        final = new Phaser.Rectangle(build2.x,build2.y,build2.width,build2.height);

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
        if (basics.contains(player.x+player.width/2,player.y+player.height/2)) {
            //Basics name for java
            window.location = "/task/getJavaTasksPage";
            //alert("You Win!!");
        }
        //check if in conditionals
        if (conditonals.contains(player.x+player.width/2,player.y+player.height/2) && !alertShown) {
            alert("Sorry,you must unlock this building");
            alertShown = true;
        }
        //check if in loops
        if (loops.contains(player.x+player.width/2,player.y+player.height/2) && !alertShown) {
            alert("Sorry,you must unlock this building");
            alertShown = true;
        }
        //check if in final
        if (final.contains(player.x+player.width/2,player.y+player.height/2) && !alertShown) {
            alert("Sorry,you must unlock this building");
            alertShown = true;
        }



    }
}

game.state.add('mainstate',mainstate);
game.state.start('mainstate');/**
 * Created by ramroop on 5/3/17.
 */
