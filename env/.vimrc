:set nocompatible

"redir! >> /home/albertnet/tmp/test.txt

au! BufWritePost .vimrc source %

":colorscheme evening
:colorscheme desert
" set the color for pop up menu for auto complete
highlight Pmenu      ctermfg=0 ctermbg=2
highlight PmenuSel   ctermfg=0 ctermbg=7
highlight PmenuSbar  ctermfg=7 ctermbg=0
highlight PmenuThumb ctermfg=0 ctermbg=7

:set scrolloff=10
:set autowrite
:set showcmd
" show matched bracket
:set showmatch
"files that leave the screen become hidden, instead of inactive
:set hidden
" a menu of possible files displays on the status line when you attempt
" to complete a filename
:set wildmenu

" used for autoindent
set tabstop=4
:set shiftwidth=4
" tab width
:set softtabstop=8

" show line number
:set number

" iterate through buffer
nmap > :bnext
nmap < :bprevious

" TODO how to express CTRL-W_p in vim script?
"nmap F ^W_p :hide

" delete consecutive blank lines
" \n line feed, the default line separator in unix,
" \r carriage return, used to input the line separator.
" remove trailing spaces
nmap <F4> :%s!^\s*\n\+!\r! :%s!\s\+$!!
" only one space is inserted whiling joining two lines
:set nojoinspaces

" exit vim
:nmap XX :wall:qa
set fileencodings=ucs-bom,utf-8,gb2312,gbk,default,latin1
" reload the file using extended unix chinese encoding
:nmap <F11> :e ++enc=euc-cn
" clear highlight after search
:nmap <F12> :nohlsearch

"regex
cmap ;\ \(\)<Left><Left>

"insert current date
iab <expr> dts strftime("%Y-%m-%d %T%z")

" search options
:set incsearch
:set hlsearch

"spelling check & highlight
:set spell spelllang=en_us
:highlight clear SpellBad
:highlight clear SpellCap
:highlight clear SpellRare
:highlight clear SpellLocal
:highlight SpellBad term=underline cterm=underline
:highlight SpellCap term=underline cterm=underline
:highlight SpellRare term=underline cterm=underline
:highlight SpellLocal term=underline cterm=underline
:set cursorline

" controlling what the backspace key does
:set backspace=indent,eol,start

" folding
:set foldcolumn=2
:set foldmethod=syntax
:set nofoldenable

" Don't screw up folds when inserting text that might affect them, until
" leaving insert mode. Foldmethod is local to the window.
autocmd InsertEnter * let w:last_fdm=&foldmethod | setlocal foldmethod=manual
autocmd InsertLeave * let &l:foldmethod=w:last_fdm

"new file type txt
:au BufRead,BufNewFile *.txt   setfiletype txt

:autocmd FileType html,xhtml set formatoptions=tqw, textwidth=149

:autocmd FileType txt set formatoptions=tqw, textwidth=149

:autocmd FileType tex set formatoptions=tqw, textwidth=149

autocmd FileType haskell set expandtab

"some maps for inputing formulas in tex
":autocmd FileType tex imap <F3> \mathbf{}
":autocmd FileType tex map <F4> :.,'as/=/\&=\&/
":autocmd FileType tex map <F5> :s/{\(\a\)}/{\\partial \1}/gc
":autocmd FileType tex map <F6> o\begin{equation}\end{equation}O

" build
nmap <F5> :make
nmap ]] :cn
nmap [[ :cp
autocmd QuickFixCmdPost [^l]* nested cwindow

:autocmd FileType c,cpp set formatoptions=tcroql, textwidth=70
	\ comments=sr:/*,mb:*,ex:*/,://

:autocmd FileType c,cpp abbreviate #i #include
:autocmd FileType c,cpp iab #d #define
:autocmd FileType c,cpp iab #b /************************************************
:autocmd FileType c,cpp iab #e <Space>************************************************/
:autocmd FileType c,cpp iab @e fprintf(stderr, "Error");

:autocmd FileType c,cpp nmap =f :w:!format % :e

:autocmd FileType java set formatoptions=tcroql, textwidth=80
	\ comments=sr:/*,mb:*,ex:*/,://

:autocmd FileType sh set formatoptions=croql, textwidth=70

:autocmd FileType php set formatoptions=tcroql, textwidth=130
	\ comments=sr:/*,mb:*,ex:*/,://

" Plugin

" auto complete
" set completeopt=longest,menu

" Latex-Suite
" REQUIRED. This makes vim invoke Latex-Suite when you open a tex file.
filetype plugin on

" IMPORTANT: grep will sometimes skip displaying the file name if you
" search in a singe file. This will confuse Latex-Suite. Set your grep
" program to always generate a file-name.
set grepprg=grep\ -nH\ $*

" This enables automatic indentation as you type.
filetype indent on

" indent/html.vim
" indent for <script> and <style>
let g:html_indent_style1 = "inc"
let g:html_indent_script1 = "inc"
let g:html_indent_inctags = "html,body,head,tbody,p,code,pre,ul,li"

" OPTIONAL: Starting with Vim 7, the filetype of empty .tex files defaults to
" 'plaintex' instead of 'tex', which results in vim-latex not being loaded.
" The following changes the default filetype back to 'tex':
let g:tex_flavor='latex'

"ragtag.vim: a set of mappings for html, xml, php
let g:ragtag_global_maps=1

""NERDTree
map <F2> :NERDTreeToggle

" supertab
let g:SuperTabLongestEnhanced = 1
let g:SuperTabLongestHighlight = 1

" taglist
nmap <F3> :TlistToggle

au BufEnter *.hs compiler ghc
let g:haddock_browser = '/usr/bin/firefox'
