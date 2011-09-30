

function observeClickyAds() {
	$(".ad").click(function(event) {
		window.location = $(this).children(".title").children(".href").attr("href");
	});
}
function observeClickyAdsEbay() {
	$(".ad").click(function(event) {
		window.location = $(this).children(".ebay-title").children(".href").attr("href");
	});
}
function observeClickyArticles() {
	$(".article").click(function(event) {
		window.location = $(this).children(".href").attr("href");
	});
}