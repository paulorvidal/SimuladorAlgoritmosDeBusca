def buscaAEstrela(tamanho, mapa):

    print('\nBusca A*: ')
    
    tempoInicio = time.time()
    nosExpandidos = 0
    pontoInicio = (0,0)
    pontoFinal = (tamanho - 1, tamanho - 1)
    
    # O ponto (0,0) é sempre 'A', então começamos com custo real = 0
    custoG_Inicial = 0
    distanciaInicial = calcularDistancia(0, 0, pontoFinal[0], pontoFinal[1])  
    custoF_Inicial = custoG_Inicial + distanciaInicial
    
    fila = [(custoF_Inicial, custoG_Inicial, pontoInicio)]
    
    matrizCustosG = []
    matrizPai = []

    # Inicializa com infinito
    for i in range(0, tamanho):
        matrizInfinita = []
        for j in range (0, tamanho):
             matrizInfinita.append(float('inf'))
        matrizCustosG.append(matrizInfinita)

    for i in range(0, tamanho):
        linhaPai = []
        for j in range(0, tamanho):
            linhaPai.append(None)
        matrizPai.append(linhaPai)
    
    matrizCustosG[0][0] = custoG_Inicial

    while len(fila) > 0:
        fila.sort()
        custoFAtual, custoGAtual, v = fila.pop(0)

        nosExpandidos += 1
        linhaAtual = v[0]
        colunaAtual = v[1]

        if v == pontoFinal:
            break
            
        # DIREITA
        if colunaAtual + 1 < tamanho:
            avaliarVizinho(linhaAtual, colunaAtual + 1, custoGAtual, v, pontoFinal, mapa, matrizCustosG, matrizPai, fila)

        # ESQUERDA
        if colunaAtual - 1 >= 0:
            avaliarVizinho(linhaAtual, colunaAtual - 1, custoGAtual, v, pontoFinal, mapa, matrizCustosG, matrizPai, fila)

        # BAIXO
        if linhaAtual + 1 < tamanho:
            avaliarVizinho(linhaAtual + 1, colunaAtual, custoGAtual, v, pontoFinal, mapa, matrizCustosG, matrizPai, fila)

        # CIMA
        if linhaAtual - 1 >= 0:
            avaliarVizinho(linhaAtual - 1, colunaAtual, custoGAtual, v, pontoFinal, mapa, matrizCustosG, matrizPai, fila)

    tempoFim = time.time()
    tempoMs = (tempoFim - tempoInicio) * 1000

    if matrizPai[pontoFinal[0]][pontoFinal[1]] is not None:
        exibirResultados(pontoFinal, nosExpandidos, tempoMs, matrizPai, mapa)
    else:
        print('Nenhum caminho encontrado ')
        print('Nós expandidos: ', nosExpandidos)
        print('Tempo de execução: ', tempoMs, ' milissegundos')