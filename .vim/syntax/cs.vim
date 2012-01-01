set foldmethod=marker
set foldmarker={,}
set foldtext=substitute(getline(v:foldstart),'{.*','{...}',)
set foldlevelstart=2 
