require 'rubygems'
require 'watir-webdriver'
browser = Watir::Browser.new :ff
browser.goto 'http://bit.ly/watir-example'

browser.link(:id, 'entry_0').click

browser.text_field(:id, 'entry_1').set 'albert'
browser.text_field(:id, 'entry_1').set ''
browser.close
