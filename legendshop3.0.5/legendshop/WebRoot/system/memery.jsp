<%
Runtime lRuntime = Runtime.getRuntime();
out.println("*** BEGIN MEMORY STATISTICS ***<br/>");
out.println("Free Memory: "+lRuntime.freeMemory()+"<br/>");
out.println("Max Memory: "+lRuntime.maxMemory()+"<br/>");
out.println("Total Memory: "+lRuntime.totalMemory()+"<br/>");
out.println("Available Processors : "+lRuntime.availableProcessors()+"<br/>");
out.println("*** END MEMORY STATISTICS ***");
%> 