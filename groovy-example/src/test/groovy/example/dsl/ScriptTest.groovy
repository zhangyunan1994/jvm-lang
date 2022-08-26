package example.dsl


def binding = new Binding()
def shell = new GroovyShell(binding)
binding.setVariable('x', 1)
binding.setVariable('y', 3)
shell.evaluate 'z = 2 * x + y'
assert binding.getVariable('z') == 5