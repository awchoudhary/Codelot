var game = new Phaser.Game(600,600,Phaser.CANVAS,'gameDiv');
var platforms;
var player;

var score = 0;
var scoreText;
var map;
var winZone;
var finalZone;
var alertShown = false; // For now, keep alert from looping infinitely

var mainstate = {
    preload:function () {
        //game.load.image('sky', '../images/sky.png');
        //game.load.image('ground', '../images/platform.png');
        //game.load.image('star', '../images/star.png');
        game.load.tilemap("ItsTheMap","../images/testMap2.json",null,Phaser.Tilemap.TILED_JSON);
        var img = game.load.image("wood_tileset","../images/wood_tileset.png");
        //var img1 = game.load.image("base_out_atlas","../images/base_out_atlas.png");
        //var img2 = game.load.image("terrain","../images/terrain.png");
        game.load.spritesheet('dude', '../images/dude.png', 32, 48);
    },

    create:function () {
        map = game.add.tilemap("ItsTheMap",32,32,64,32);
        map.addTilesetImage("wood_tileset","wood_tileset");
        //map.addTilesetImage("base_out_atlas","base_out_atlas");
        //map.addTilesetImage("terrain","terrain");
        map.createLayer("GroundLayer").resizeWorld();
        map.createLayer("2ndLayer").resizeWorld();


        //player
        player = new Phaser.Sprite(game,0,0,"dude");
        game.world.addAt(player,2);
        game.camera.follow(player);


        //Get Objects from Object Layer
        var start =map.objects["ObjLayer"][0];
        //alert(start);
        var end = map.objects["ObjLayer"][1];
        var final = map.objects["ObjLayer"][2];

        //define winzone
        winZone = new Phaser.Rectangle(end.x,end.y,end.width,end.height);
        finalZone = new Phaser.Rectangle(final.x,final.y,final.width,final.height);

        //set player to starting point
        player.position.set(start.x,start.y);






        // //  We're going to be using physics, so enable the Arcade Physics system
        //game.physics.startSystem(Phaser.Physics.ARCADE);
        //
        // //  A simple background for our game
        // game.add.sprite(0, 0, 'sky');
        //
        // //  The platforms group contains the ground and the 2 ledges we can jump on
        // platforms = game.add.group();
        //
        // //  We will enable physics for any object that is created in this group
        // platforms.enableBody = true;
        //
        // // Here we create the ground.
        // var ground = platforms.create(0, game.world.height - 64, 'ground');
        //
        // //  Scale it to fit the width of the game (the original sprite is 400x32 in size)
        // ground.scale.setTo(2, 2);
        //
        // //  This stops it from falling away when you jump on it
        // ground.body.immovable = true;
        //
        // //  Now let's create two ledges
        // var ledge = platforms.create(400, 400, 'ground');
        //
        // ledge.body.immovable = true;
        //
        // ledge = platforms.create(-150, 250, 'ground');
        //
        // ledge.body.immovable = true;
        //
        //
        // // The player and its settings
        // player = game.add.sprite(32, game.world.height - 150, 'dude');
        //
        // //  We need to enable physics on the player
        // game.physics.arcade.enable(player);
        //
        // //  Player physics properties. Give the little guy a slight bounce.
        // player.body.bounce.y = 0.2;
        // player.body.gravity.y = 300;
        // player.body.collideWorldBounds = true;
        //
        // //  Our two animations, walking left and right.
        player.animations.add('left', [0, 1, 2, 3], 10, true);
        player.animations.add('right', [5, 6, 7, 8], 10, true);
        //
        //
        // stars = game.add.group();
        //
        // stars.enableBody = true;
        //
        // //  Here we'll create 12 of them evenly spaced apart
        // for (var i = 0; i < 12; i++)
        // {
        //     //  Create a star inside of the 'stars' group
        //     var star = stars.create(i * 70, 0, 'star');
        //
        //     //  Let gravity do its thing
        //     star.body.gravity.y = 6;
        //
        //     //  This just gives each star a slightly random bounce value
        //     star.body.bounce.y = 0.7 + Math.random() * 0.2;
        // }
        //
        //
        // scoreText = game.add.text(16, 16, 'score: 0', { fontSize: '32px', fill: '#000' });
    },

    update:function () {

        // var hitPlatform = game.physics.arcade.collide(player, platforms);
        //
        //cursors = game.input.keyboard.createCursorKeys();
        //
        // //  Reset the players velocity (movement)
        // player.body.velocity.x = 0;
        //
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
        }
        else if(game.input.keyboard.isDown(Phaser.Keyboard.DOWN))
        {
            player.y +=4;
        }
        else
        {
            //  Stand still
            player.animations.stop();

            player.frame = 4;
        }


        if (winZone.contains(player.x+player.width/2,player.y+player.height/2)) {
            window.location = "/task/getJavaTasksPage";
            //alert("You Win!!");
        }
        if (finalZone.contains(player.x+player.width/2,player.y+player.height/2) && !alertShown) {
            alert("Sorry,you must unlock this building");
            alertShown = true;
        }
        //
        // //  Allow the player to jump if they are touching the ground.
        // if (cursors.up.isDown && player.body.touching.down && hitPlatform)
        // {
        //     player.body.velocity.y = -350;
        // }
        //
        //
        // function collectStar (player, star) {
        //
        //     // Removes the star from the screen
        //     star.kill();
        //
        //     //  Add and update the score
        //     score += 10;
        //     scoreText.text = 'Score: ' + score;
        //
        // }
        //
        // game.physics.arcade.collide(stars, platforms);
        // game.physics.arcade.overlap(player, stars, collectStar, null, this);

    }
}

game.state.add('mainstate',mainstate);
game.state.start('mainstate');