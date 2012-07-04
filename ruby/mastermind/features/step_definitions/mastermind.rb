def messenger
	@messenger ||= StringIO.new
end
def game
	@game ||= Mastermind::Game.new(messenger)
end
def messages_should_include(message)
	messenger.string.split("\n" ).should include(message)
end

Given /^I am not yet playing$/ do
end
When /^I start a new game$/ do
	@messenger = StringIO.new
	game = Mastermind::Game.new(@messenger)
	game.start(nil)
end
Then /^the game should say "(.*)"$/ do |message|
	messages_should_include message
end

Given /^the secret code is (. . . .)$/ do |code|
	game.start(code.split)
end
When /^I guess (. . . .)$/ do |code|
	game.guess(code.split)
end
Then /^the mark should be (.*)$/ do |mark|
	messages_should_include mark
end