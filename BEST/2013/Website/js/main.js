/**
 * Westwood Robotics BEST 2013
 * Website Javascript
 *
 *
 * Copyright 2013 - Westwood Robotics
 */
var sections = [], offsets = [];
var dropdowns = [];

//Copyright - 2013 Westood Robotics
// Set the current navigation link
function setNav(section) {
	$('#nav ul li').removeClass('current');
	$('#nav ul').children('.sl').filter(function (i) {
		var text = $(this).children('a').prop('href');
		text = text.split('#/')[1];
		return text == section;
	}).addClass('current');
}


// Scroll to the specified section on the page
function smoothScrollTo(section) {
	jQuery.scrollTo.window().queue([]).stop();
	$.scrollTo("#" + section, 750, {easing: 'easeOutExpo', offset: {top: 0, left: -125}});
	return true;
}

function nextSection(section) {
	smoothScrollTo(sections[sections.indexOf($(section).closest('.section')[0].id)+1]);
	return true;
}

function prevSection(section) {
	smoothScrollTo(sections[sections.indexOf($(section).closest('.section')[0].id)-1]);
	return true;
}

function toggleDropdown(type)
{
	closeAllDrops();
	var drop = $('#' + type);
	if(drop.is(":hidden"))
	{
		if(jQuery.effects){
			drop.show("slide", { direction: "up" }, 200);
		}else{
			drop.show();
		}
	}else{
		if(jQuery.effects){
			drop.hide("slide", { direction: "up" }, 200);
		}else{
			drop.hide();
		}
	}
	return true;
}

function closeAllDrops()
{
	for (var i = 0; i < dropdowns.length; i++) {
		var drop = $('#' + dropdowns[i]);
		if(!drop.is(":hidden"))
		{
			if(jQuery.effects){
				drop.hide("slide", { direction: "up" }, 200);
			}else{
				drop.hide();
			}
		}

	};
	return true;
}

function closeWarning()
{
	$('#ns-warning').remove();
	return true;
}


// Hash change event handler
$(window).on('hashchange', function (e) {
	var hash = location.hash.substring(2).split("/");
	
	var section = hash[0].toLowerCase();
	
	if (section.length > 0) {
		if (sections.indexOf(section) < 0) return;
	
		setNav(section);
		smoothScrollTo(section);
	}
});

//Copyright - 2013 Westood Robotics
// Scroll event handler
$(document).scroll(function (e) {
	
	// Check to see what section is currently in the window view
	var sp = $(document).scrollLeft() + 175;
	var ik;

	for (ik = 0; ik < sections.length; ik++) {
		if (offsets[ik] <= sp && sp < offsets[ik+1]) {
			setNav(sections[ik]);
			break;	
		}
	};	
});

var imgData = [
	{
		image: 'photos/01.png',
		description: 'Alex, Issac and Yusuf turn string into rope'
	},
	{
		image: 'photos/02.png',
		description: 'Making changes at the Test Drive'
	},
	{
		image: 'photos/03.png',
		description: 'Working on dimensions'
	},
	{
		image: 'photos/04.png',
		description: 'Drawing the wiring schematic for our display board'
	},
	{
		image: 'photos/05.png',
		description: 'Madan cuts some parts for our new grabbers'
	},
	{
		image: 'photos/06.png',
		description: 'Mounting the track system to move robot in and out'
	},
	{
		image: 'photos/07.png',
		description: 'Sumeet doing some delicate work'
	},
	{
		image: 'photos/08.png',
		description: 'Team Discussion'
	},
	{
		image: 'photos/09.png',
		description: 'Our display board in the making'
	},
	{
		image: 'photos/10.png',
		description: 'Details for the trolley system'
	}
];

//Copyright - 2013 Westood Robotics
// Document fully loaded
$(document).ready(function () {

	/**
	 *
	 * No Support Warning for IE Users
	 * Taken from MY website: djwolf.no-ip.org
	 *
	 *
	 * Copyright 2013 - Austin Reuland
	 */
	if ($.browser.msie){
		var message = '<span onclick="closeWarning()">X</span>';
		if($.browser.msie){
			message += "This website has some special tricks and other goodies. It works best with <a href='http://chrome.google.com'>Google Chrome</a>.";
		}else{
			message += "Whatever browser you have, its not supported. Go try <a href='http://chrome.google.com'>Google Chrome</a>.";
		}
		div = $('<div id="ns-warning"></div>').html(message).hide().find('a').css({color:'#333'}).end();
		div.appendTo('#content').slideDown(500);
	}
	//Copyright 2013 - Austin Reuland

	var sectionstemp = document.getElementsByClassName('section');
	for (var is = 0; is < sectionstemp.length; is++) {
		sections.push(sectionstemp[is].id);
	};

	var dropstemp = document.getElementsByClassName('drop');
	for (var is = 0; is < dropstemp.length; is++) {
		dropdowns.push(dropstemp[is].id);
	};
	
	// Calculate section vertical offsets
	// (used for determining which section of the document the user is currently viewing)
	for (var i = 0, n = sections.length; i < n; i++) {
		offsets.push(Math.floor($('#' + sections[i]).offset().left));
	}
	offsets[0] = offsets[0] - 100;
	offsets.push(document.width);
	
	// Check if user navigated to a pre-hashed URL and view that section
	// otherwise set the hash to the first section
	if (location.hash) {
		$(window).trigger('hashchange');
	} else {
		location.hash = "/" + sections[0];
	}

	// Handle clicking of nav links when in the same section specified by the hash
	// (should scroll back to the beginning of the section)
	$('#nav ul li a').click(function (e) {
		var href = $(this).prop('href');
		href = href.substring(href.indexOf('#/'));
		// console.log(href, location.hash);
		if (href == location.hash) {
			var section = href.split('#/')[1];
			smoothScrollTo(section);
		}
	});

	document.onmousewheel=(function(){jQuery.scrollTo.window().queue([]).stop();});
	
	// Initialize the parallax background effect
	$.stellar({parallaxElements: false});
	
	// Initialize the photo gallery
	Galleria.loadTheme('galleria-classic/galleria.classic.min.js');
	Galleria.run('#galleria', { dataSource: imgData });
	
});

//Copyright - 2013 Westood Robotics