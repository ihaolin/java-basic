function SimpleGreeter(salutation){
	this.salutation = salutation;
}
SimpleGreeter.prototype.greet = function(whom){
									return this.salutation;
								}