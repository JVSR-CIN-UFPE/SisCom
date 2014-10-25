import math
# tag = {id='?'}

# Estimador Lower Bound
def lowerBoundTags(sucesso, colisao):
    return sucesso + 2 * colisao

def lowerBoundFrame(colisao):
    return 2 * colisao

# codigo de estimador
def estimadorTags(sucesso, colisao, vazio):
    return lowerBoundFrame(sucesso, colisao)

def definirTags(L, quant_tags):
    slottes = [0 for i in range(0, L) ]
    print slottes
    for i in xrange(0, quant_tags):
        index = math.random(0, L)
        slottes[index] += 1

def main():
    quant_tags = 50
    L = 64
    tags_estimado = 
