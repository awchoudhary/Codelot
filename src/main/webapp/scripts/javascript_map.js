//Variables used in game

//Create canvas to display the game,'game' will refer to this canvas element
var game = new Phaser.Game(1000,600,Phaser.CANVAS,'gameDiv');
var player;


var map;
var layer;
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
var start;
var popup1;
var popup2;
var popup3;

var tween = null;
var progress;
var completed;

//npc locations if player in location trigger dialog box
var npcLoc1;
var npcLoc2;
var npcLoc3;

//dragon sprite,spawn only if didnt complete map
var dragonS;
var dragonLocations;
//iterate through dragonLocation array
var i = 0;

var mainstate = {
    preload:function () {

        //Load in JsonFile for the tileMap + needed tilesets to render the map
        game.load.tilemap("ItsTheMap","../images/tilesets/javascript_map.json",null,Phaser.Tilemap.TILED_JSON);
        game.load.image("medieval_terrain","../images/tilesets/medieval_terrain.png");
        game.load.image("medieval_houses","../images/tilesets/medieval_houses.png");
        game.load.image("medieval_animals","../images/tilesets/medieval_animals.png");
        game.load.image("TileC","../images/tilesets/TileC.png");


        //get users sprite and load appropriate sprite sheet
        var spritesheet = document.getElementById('avtr').getAttribute('src');
        spritesheet = spritesheet.slice(3,spritesheet.length-4);
        spritesheet = spritesheet.concat('_spritesheet.png');
        game.load.spritesheet('sprite', spritesheet, 32,32);

        //npc spritesheets
        game.load.spritesheet('npc1','../images/npc1_sprite.png',32,32);
        game.load.spritesheet('npc2','../images/npc3_sprite.png',32,32);
        game.load.spritesheet('npc3','../images/npc3_sprite.png',32,32);


        //get the progress of the user
        progress = document.getElementById('progess_message').innerHTML;
        progress = progress.substring(0,progress.indexOf('%') );

        //if progress = 100 then load completed dialog boxes
        if (progress=="100") {
            //dialog boxes
            game.load.image('dialog1','../images/dialog1_completed.png');
            game.load.image('dialog2','../images/dialog2_completed.png');
            game.load.image('dialog3','../images/dialog3_completed.png');
            game.load.image('close', '../images/orb-red.png');



        }
        //else load the incompleted boxes+ dragon sprite
        else {
            completed = false;
            //dialog boxes
            game.load.image('dialog1','../images/dialog1_incomplete.png');
            game.load.image('dialog2','../images/dialog2_incomplete.png');
            game.load.image('dialog3','../images/dialog3_incomplete.png');
            game.load.image('close', '../images/orb-red.png');

            game.load.spritesheet('dragon','../images/dragon_sprite.png',161,123);

        }

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
        layer = map.createLayer("Collision");
        layer.visible = false;



        //get objects for the tasks

        //start position
        start =map.objects["tasks"][0];

        //basics
        basics = map.objects["tasks"][1];

        //final
        conditionals = map.objects["tasks"][2];

        //conditionals
        loops = map.objects["tasks"][3];

        //loops
        final = map.objects["tasks"][4];

        //location 1st npc
        var sprite1 = map.objects["tasks"][5];

        //2nd npc location
        var sprite2 = map.objects["tasks"][6];

        //3rd npc location
        var sprite3 = map.objects["tasks"][7];




        //define basics zone
        basicsZone = new Phaser.Rectangle(basics.x,basics.y,basics.width,basics.height);

        //define conditionals zone
        conditionalsZone = new Phaser.Rectangle(conditionals.x,conditionals.y,conditionals.width,conditionals.height);


        //define loops zone
        loopsZone = new Phaser.Rectangle(loops.x,loops.y,loops.width,loops.height);

        //define final zone
        finalZone = new Phaser.Rectangle(final.x,final.y,final.width,final.height);

        //define npc1 location
        npcLoc1 = new Phaser.Rectangle(sprite1.x,sprite1.y,sprite1.width,sprite1.height);

        //define npc2 location
        npcLoc2 = new Phaser.Rectangle(sprite2.x,sprite2.y,sprite2.width,sprite2.height);

        //define npc3 location
        npcLoc3 = new Phaser.Rectangle(sprite3.x,sprite3.y,sprite3.width,sprite3.height);



        //get player
        player = new Phaser.Sprite(game,start.x,start.y,"sprite");


        /// add npc
        var npc1 = new Phaser.Sprite(game,sprite1.x,sprite1.y,"npc1");
        npc1.scale.setTo(1.2, 1.2);

        var npc2 = new Phaser.Sprite(game,sprite2.x,sprite2.y,"npc2");
        npc2.scale.setTo(1.2, 1.2);

        var npc3 = new Phaser.Sprite(game,sprite3.x,sprite3.y,"npc3");
        npc3.scale.setTo(1.2, 1.2);

        //if not completed add dragon sprite to game
        if(completed == false) {
            dragonS = new Phaser.Sprite(game,loops.x-470,loops.y,"dragon");
        }
        //dialog boxes for the game

        //1st box
        popup1 = game.add.sprite(sprite1.x,sprite1.y,'dialog1');
        popup1.alpha = 0.8;
        popup1.anchor.set(0.5);
        popup1.inputEnabled = true;
        popup1.input.enableDrag();
        popup1.visible = false;

        var pw = (popup1.width /2) -30;
        var ph = (popup1.height/ 2) - 8;
        var closeButton = game.make.sprite(pw,-ph,'close');
        closeButton.inputEnabled = true;
        closeButton.input.priorityID = 1;
        closeButton.input.useHandCursor = true;
        closeButton.events.onInputDown.add(closeWindow1,this);
        popup1.addChild(closeButton);

        //2nd Box
        popup2 = game.add.sprite(sprite2.x,sprite2.y,'dialog2');
        popup2.alpha = 0.8;
        popup2.anchor.set(0.5);
        popup2.inputEnabled = true;
        popup2.input.enableDrag();
        popup2.visible = false;

        var pw2 = (popup1.width /2) -30;
        var ph2 = (popup1.height/ 2) - 8;
        var closeButton2 = game.make.sprite(pw2,-ph2,'close');
        closeButton2.inputEnabled = true;
        closeButton2.input.priorityID = 1;
        closeButton2.input.useHandCursor = true;
        closeButton2.events.onInputDown.add(closeWindow2,this);
        popup2.addChild(closeButton2);



        //3rd Box
        popup3 = game.add.sprite(sprite3.x,sprite3.y,'dialog3');
        popup3.alpha = 0.8;
        popup3.anchor.set(0.5);
        popup3.inputEnabled = true;
        popup3.input.enableDrag();
        popup3.visible = false;

        var pw3 = (popup1.width /2) -30;
        var ph3 = (popup1.height/ 2) - 8;
        var closeButton3 = game.make.sprite(pw3,-ph3,'close');
        closeButton3.inputEnabled = true;
        closeButton3.input.priorityID = 1;
        closeButton3.input.useHandCursor = true;
        closeButton3.events.onInputDown.add(closeWindow3,this);
        popup3.addChild(closeButton3);



        //add player
        game.world.addAt(player,2);

        //add npcs
        game.world.addAt(npc1,2);
        game.world.addAt(npc2,2);
        game.world.addAt(npc3,2);

        if (completed==false) {
            game.world.addAt(dragonS,2);
        }


        game.camera.follow(player,Phaser.Camera.FOLLOW_LOCKON,0.1,0.1);


        //Handle collisions
        game.physics.startSystem(Phaser.Physics.P2JS);
        map.setCollision(1711,true,"Collision");
        game.physics.p2.convertTilemap(map, "Collision");
        game.physics.p2.enable(player);
        player.body.fixedRotation = true;




        //Animations for player up corresponds to frame 0,1 in spritesheet,etc.

        player.animations.add('up', [0, 1], 10, true);
        player.animations.add('down', [2, 3], 10, true);
        player.animations.add('left', [4,5], 10, true);
        player.animations.add('right', [6,7], 10, true);

        //set up dragon locations
        dragonLocations = [basics,loops,conditionals,final];
    },

    update:function () {


        var speed=200;
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
            post('/tasks/task', {languageCode: '20', numBuilding: '0', floorNum: '-1'});
        }
        //check if in conditionals
        if (conditionalsZone.contains(player.x+player.width/2,player.y+player.height/2)) {
            post('/tasks/task', {languageCode: '20', numBuilding: '1', floorNum: '-1'});
        }
        //check if in loops
        if (loopsZone.contains(player.x+player.width/2,player.y+player.height/2)) {
            post('/tasks/task', {languageCode: '20', numBuilding: '2', floorNum: '-1'});
        }
        //check if in final
        if (finalZone.contains(player.x+player.width/2,player.y+player.height/2) ) {
            post('/tasks/task', {languageCode: '20', numBuilding: '3', floorNum: '-1'});

        }

        if (npcLoc1.contains(player.x+player.width/2,player.y+player.height/2) ) {
            console.log(progress);
            popup1.visible = true;
            openWindow(popup1);
            player.body.x = player.x;
            player.body.y = player.y +50;
            if (completed!=true) {
                if (i>=dragonLocations.length)  {
                    i = 0;
                }
                dragonS.x = dragonLocations[i].x + 50;
                dragonS.y = dragonLocations[i].y;
                i = i + 1;
            }



        }

        if (npcLoc2.contains(player.x+player.width/2,player.y+player.height/2) ) {
            console.log(progress);
            popup2.visible = true;
            openWindow(popup2);
            player.body.x = player.x;
            player.body.y = player.y +50;
            if (completed!=true) {
                if (i>=dragonLocations.length)  {
                    i = 0;
                }
                dragonS.x = dragonLocations[i].x + 60 ;
                dragonS.y = dragonLocations[i].y;
                i = i + 1;
            }



        }

        if (npcLoc3.contains(player.x+player.width/2,player.y+player.height/2) ) {
            console.log(progress);
            popup3.visible = true;
            openWindow(popup3);
            player.body.x = player.x;
            player.body.y = player.y +50;
            if (completed!=true) {
                if (i>=dragonLocations.length)  {
                    i = 0;
                }
                dragonS.x = dragonLocations[i].x+ 50 ;
                dragonS.y = dragonLocations[i].y;
                i = i + 1;
            }


        }

    }
}


function openWindow(X) {
    if ((tween !== null && tween.isRunning) || X.scale.x === 1)
    {
        return;
    }

    //  Create a tween that will pop-open the window, but only if it's not already tweening or open
    tween = game.add.tween(X.scale).to( { x: 1, y: 1 }, 1000, Phaser.Easing.Elastic.Out, true);
}

function closeWindow1() {
    if (tween && tween.isRunning || popup1.scale.x === 0.1)
    {
        return;
    }

    //  Create a tween that will close the window, but only if it's not already tweening or closed
    tween = game.add.tween(popup1.scale).to( { x: 0.1, y: 0.1 }, 500, Phaser.Easing.Elastic.In, true);
    popup1.visible = false;

}

function closeWindow2() {
    if (tween && tween.isRunning || popup2.scale.x === 0.1)
    {
        return;
    }

    //  Create a tween that will close the window, but only if it's not already tweening or closed
    tween = game.add.tween(popup2.scale).to( { x: 0.1, y: 0.1 }, 500, Phaser.Easing.Elastic.In, true);
    popup2.visible = false;

}

function closeWindow3() {
    if (tween && tween.isRunning || popup3.scale.x === 0.1)
    {
        return;
    }

    //  Create a tween that will close the window, but only if it's not already tweening or closed
    tween = game.add.tween(popup3.scale).to( { x: 0.1, y: 0.1 }, 500, Phaser.Easing.Elastic.In, true);
    popup3.visible = false;

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
