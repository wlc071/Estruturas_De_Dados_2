/* Projeto...: Oredenação
 * Autor.....: Professor Dreco
 * Biblioteca: Vetor de até 1.000.000 posições
 * Classe....: Vetor_Geral
 * Objetivo..: Criação do nó (elemento) que será utilizado nos projetos
 */
package vetorgeral;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class VetorGeral
{
    int vetor[] = new int[1000000];
    
    private int tamanho = 1000000;
    
    private int limite  = 1000000; 
    
    public void setPosicao (Integer pos, Integer valor)
    {
        vetor[pos] = valor;
    }

    public Integer getPosicao(Integer pos)
    {
        return vetor[pos];
    }
    
    public void setTamanho (Integer tam)
    {
        tamanho = tam;
    }

    public Integer getTamanho()
    {
        return tamanho;
    }
    
    public void setLimite (Integer lim)
    {
        limite = lim;
    }

    public Integer getLimite()
    {
        return limite;
    }

    public void mostra_Vetor (int limite)
    {
        int i = 0;
        String limite_texto = "   1000";
        
        switch (limite)
        {
            case 10000:
                limite_texto = "  10000";
            case 100000:
                limite_texto = " 100000";
            case 1000000:
                limite_texto = "1000000";
        }

        System.out.println("+---------+------------------+");
        System.out.println("| Vetor de "+limite_texto+" posições  |");
        System.out.println("+---------+------------------+");
        System.out.println("| Posicao | Valor            |");
        System.out.println("+---------+------------------+");
        
        for (i = 0; i < limite; i++)
        {
            System.out.format ("| %7d | %16d |\n", i, vetor[i]);
        }
        System.out.println("+---------+------------------+");
    }
    static int contadores = 0, trocadores = 0, interadores = 0;

    public void inicializarVetor()
    {
        int t = this.getTamanho();
        int i = 0;
        
        for (i = 0; i <= t; i++)
        {
            this.vetor[i] = 0;
        }

        this.setTamanho(0);
    }

    public void troca_de_Lugar(int o, int d)
    {
        int temp = this.vetor[d];
        this.vetor[d] = this.vetor[o];
        this.vetor[o] = temp;
    }

    public void troca_de_Prioridade(int o, int d)
    {
        int temp = this.vetor[o];
        int i = 0;
        
        if (o < d)
        {
            for (i = o; i < d; i++)
            {
                this.vetor[i] = this.vetor[i+1];
            }
        }
        else
        {
            for (i = o; i > d; i--)
            {
                this.vetor[i] = this.vetor[i-1];
            }
        }
        this.vetor[d] = temp;
    }

    public Integer gera_Numeros (Integer quantidade)
    {
        MsgVetor.msg_central ("Iniciando a geração de números aleatórios para a lista.");
        
        int numero = 0;
        int gerados = 0;
        
        if (quantidade <= 0)
        {
            quantidade = 1000;
        }
        else
        {
            quantidade++;
        }
        
        Random r = new Random();
        
        for (gerados = 1; gerados < quantidade; gerados++)
        {
            numero = r.nextInt();
            
            this.vetor[gerados-1] = numero;
        }
        
        gerados--;
        
        this.setTamanho(gerados);
        this.setLimite(gerados);
        
        this.consultar_geral();
        
        return gerados;
    }

    public void ordena_BubbleSort()
    {
        MsgVetor.msg_central ("BubbleSort - EXECUTADO.");
        long tempoInicial = System.currentTimeMillis();
        Estatistica e = new Estatistica();
        int aux = 0;
        int i = 0;
        int tamanho = this.getTamanho();
        e.interacoes = e.condicoes = e.trocas = 0;
         
        for(i = 0; i<tamanho; i++){
            e.interacoes++;
		for(int j = 0; j<tamanho-1; j++){
                    e.interacoes++;
                    e.condicoes++;
                    if(this.vetor[j] > this.vetor[j + 1]){
                        aux = this.vetor[j];
			this.vetor[j] = this.vetor[j+1];
			this.vetor[j+1] = aux;
                        e.trocas++;
                    }
		}
	}
        long tempoFinal = System.currentTimeMillis();
        e.mostrar(tamanho, tempoInicial, tempoFinal);

    } 

    public void ordena_SelectionSort()
    {
         MsgVetor.msg_central ("SelectionSort - Aguarde implantação.");
         
        long tempoInicial = System.currentTimeMillis();
        Estatistica e = new Estatistica();
        e.interacoes = e.condicoes = e.trocas = 0;
        int aux = 0;
        int i = 0;
        int tamanho = this.getTamanho();
         
         for (i = 0; i < tamanho; i++) {
		e.interacoes++;
		int i_menor = i;
		for (int j = i + 1; j < tamanho; j++){
                    e.interacoes++;
                    e.condicoes++;
                    if (this.vetor[j] < this.vetor[i_menor])
			i_menor = j;
                }
		e.trocas++;
		aux = this.vetor[i];
		this.vetor[i] = this.vetor[i_menor];
		this.vetor[i_menor] = aux;
	
	}
         
        long tempoFinal = System.currentTimeMillis();
        e.mostrar(tamanho, tempoInicial, tempoFinal);
    } 

    public void ordena_InsertionSort()
    {
        MsgVetor.msg_central ("InsertionSort - Aguarde implantação.");
        long tempoInicial = System.currentTimeMillis();
        Estatistica e = new Estatistica();
        e.interacoes = e.condicoes = e.trocas = 0;
        int aux = 0;
        int i = 0;
        int tamanho = this.getTamanho();

        for (i = 1; i < tamanho; i++){
            e.interacoes++;
            aux = this.vetor[i];
            int j = i;
			
            while ((j > 0) && (this.vetor[j-1] > aux)){
                e.interacoes++;
		this.vetor[j] = this.vetor[j-1];
		j -= 1;
                e.trocas++;
            }
            this.vetor[j] = aux;
            
	}
        
        long tempoFinal = System.currentTimeMillis();
        e.mostrar(tamanho, tempoInicial, tempoFinal);

    } 
    
    

    public static void mergeSort(int[] v, int[] m, int ini, int fim){
        contadores++;
        if (ini < fim){
            int meio = (ini + fim)/2;
            mergeSort(v, m, ini, meio);
            mergeSort(v, m, meio+1, fim);
            intercalarMerge(v, m, ini, meio, fim);
        }
    }
    public static void intercalarMerge(int[] v, int[] m, int ini, int meio, int fim){
        for (int k = ini; k <= fim; k++){
            interadores++;
            m[k] = v[k];
            trocadores++;
        }
        int i = ini;
        int j = meio+1;
        for(int k = ini; k<= fim; k++){
            interadores++;
            contadores++;
            if (i > meio) {
                v[k] = m[j++];
                trocadores++;
                
            }
            else if (j > fim){
                contadores++;
                v[k] = m[i++];
                trocadores++;
            }
            else if (m[i] < m[j]){
                contadores++;
                v[k] = m[i++];
                trocadores++;
            }
            else {
                contadores++;
                v[k] = m[j++];
                trocadores++;
            }
        }
    }
    
    public void ordena_MergeSort()
    {
        MsgVetor.msg_central ("MergeSort - OK.");
        contadores =  trocadores = interadores = 0;
        
        long tempoInicial = System.currentTimeMillis();
        Estatistica e = new Estatistica();

        int vt = this.getTamanho();
        int[] m = new int[vt];
             
        mergeSort(this.vetor, m, 0,(vt-1));
        
        e.condicoes = contadores;
        e.interacoes = interadores;
        e.trocas = trocadores;

        long tempoFinal = System.currentTimeMillis();
        e.mostrar(tamanho, tempoInicial, tempoFinal);

    } 
    
    
    

    public void ordena_ShellSort()
    {
        MsgVetor.msg_central ("ShellSort - Aguarde implantação.");
        
        long tempoInicial = System.currentTimeMillis();
        Estatistica e = new Estatistica();
        e.interacoes = e.condicoes = e.trocas = 0;
        
        int i , j , temp, tam = this.getTamanho();;
	    
	int incremento = 1;
	while(incremento < tam) {
            e.interacoes++;
	    incremento = 3 * incremento + 1;
	}
	     
	while (incremento > 1) {
            e.interacoes++;
	    incremento /= 3;
	        
	    for(i = incremento; i < tam; i++) {
                e.interacoes++;
	        temp = vetor[i];
	        j = i - incremento;
	        while (j >= 0 && temp < vetor[j]) {
                    e.interacoes++;
	            vetor[j + incremento] = vetor[j];
	            j -= incremento;
                    e.trocas++;
	        }
	        vetor[j + incremento] = temp;
	    }
	}	    
        
        long tempoFinal = System.currentTimeMillis();
        e.mostrar(tamanho, tempoInicial, tempoFinal);
    } 
    
    

    public static void quick(int v[], int esquerda, int direita) {
        
        int pivo = v[direita + (esquerda - direita) / 2];
        int i = esquerda;
        int f = direita;

        while (i <= f) {
            interadores++;
            while ( v[i] < pivo) {
                i++;
                interadores++;
            }
            while (v[f] > pivo) {
                f--;
                interadores++;
            }
            contadores++;
            if (i <= f) {
                int aux = v[f];
                v[f] = v[i];
                v[i] = aux;
                i++;
                f--;  
                trocadores++;
            }            
        }
        contadores++;
        if (esquerda < f) {
            quick(v, esquerda, f);
        }
        contadores++;
        if (i < direita) {
            quick(v, i, direita);
        }
    }
 
    public void ordena_QuickSort()
    {
        contadores =  trocadores = interadores = 0;
        long tempoInicial = System.currentTimeMillis();
        Estatistica e = new Estatistica();
        
        MsgVetor.msg_central ("QuickSort - OK.");
        
        int vt = this.getTamanho();
             
        quick(this.vetor,0,(vt-1));
        
        e.condicoes = contadores;
        e.interacoes = interadores;
        e.trocas = trocadores;

        long tempoFinal = System.currentTimeMillis();
        e.mostrar(tamanho, tempoInicial, tempoFinal);
            
    } 
    
    
    public static void aplicarHeap(int[] vetor, int n, int i){
        int raiz = i;
        int esquerda = 2*i + 1;
        int direita = 2*i + 2;
        
        contadores++;
        if (esquerda < n && vetor[esquerda] > vetor[raiz]){
            raiz = esquerda;
        }
        contadores++;
        if (direita < n && vetor[direita] > vetor[raiz]){
            raiz = direita;
        }
        contadores++;
        if (raiz != i){
            trocadores++;
            int aux = vetor[i];
            vetor[i] = vetor[raiz];
            vetor[raiz] = aux;
            
            aplicarHeap(vetor, n, raiz);
        }
    }

    public void ordena_HeapSort()
    {
        MsgVetor.msg_central ("HeapSort - OK.");
        contadores =  trocadores = interadores = 0;
        long tempoInicial = System.currentTimeMillis();
        Estatistica e = new Estatistica();
        
        int vt = this.getTamanho();
        
        
        for(int i = vt / 2 - 1; i >= 0; i--){
            aplicarHeap(vetor, vt, i);
            interadores++;
        }
         
        for(int j = vt-1; j > 0; j--){
            trocadores++;
            int aux = vetor[0];
            vetor[0] = vetor[j];
            vetor[j] = aux;
            interadores++;
            aplicarHeap(vetor, j, 0);
        } 
        
        e.condicoes = contadores;
        e.interacoes = interadores;
        e.trocas = trocadores;

        long tempoFinal = System.currentTimeMillis();
        e.mostrar(tamanho, tempoInicial, tempoFinal);
    } 

    public void ordena_DrecoSort()
    {
        Estatistica e = new Estatistica();
        long tempoInicial = System.currentTimeMillis();
        
        int vli = 0;
        int vlj = 0;
        int vlx = 0;
        int vln = this.getTamanho();
        int vllim = vln - 1;
        
        MsgVetor.msg_central("n="+vln);
        
        while (vli < vln) 
        {
            e.interacoes++;

            vlj = 0;
            
            while (vlj <= vllim)
            {

                e.interacoes++;
                e.condicoes++;
                
                if (this.vetor[vlj] > this.vetor[vlj+1])
                {
                    e.trocas++;
                    
                    vlx = this.vetor[vlj];
                    this.vetor[vlj]   = this.vetor[vlj+1];
                    this.vetor[vlj+1] = vlx;
                }
                vlj = vlj + 1;
            }
            vli = vli + 1;
        }
        
        long tempoFinal = System.currentTimeMillis();
        
        e.mostrar(this.getTamanho(), tempoInicial, tempoFinal);
    } 
    
    
    
    
    public void ordena_ProcessoX()
    {
        MsgVetor.msg_central ("Processo 9 - Cocktail sort.");
        Estatistica e = new Estatistica();
        long tempoInicial = System.currentTimeMillis();
        int n = this.getTamanho(); 
        
        int inicio, fim, swap, aux;
        inicio = 0;
        fim = n - 1;
        swap = 0;
        while (swap == 0 && inicio < fim)
        {
            e.interacoes++;
            swap = 1;
            for (int i = inicio; i < fim; i = i + 1)
            {
                e.interacoes++;
                e.condicoes++;
                if (vetor[i] > vetor[i + 1])
                {
                    aux = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = aux;
                    swap = 0;
                    e.trocas++;
                }
            }
            fim = fim - 1;
            for (int i = fim; i > inicio; i = i - 1)
            {
                e.interacoes++;
                e.condicoes++;
                if (vetor[i] < vetor[i - 1])
                {
                    aux = vetor[i];
                    vetor[i] = vetor[i - 1];
                    vetor[i - 1] = aux;
                    swap = 0;
                    e.trocas++;
                }
            }
            inicio = inicio + 1;
        }
        long tempoFinal = System.currentTimeMillis();
        
        e.mostrar(this.getTamanho(), tempoInicial, tempoFinal);

    }
    
    public void ordena_ProcesoY(){
        MsgVetor.msg_central ("Processo - ");
        
        
    }
    
    public int[] tabela_Indices(int i, int j, int[] vetor, int qtdIndices, int qtdGrupo){
        int[] tabIndices = new int[qtdIndices];
        for( i = 0, j = 0; i < qtdIndices; i++, j+= qtdGrupo){
            int n = vetor[j];
            tabIndices[i] = n;
        }
        return tabIndices;
    }
    
    public void tabela_Indices_Gerados(){
        int index = 0, tamanho = this.getTamanho(), i = 0, j = 0, k = 0, ok = 0;
        int[] tabIndices = new int[100];
        tabIndices = tabela_Indices(0, 0, vetor, 100, 1000);
        System.out.println("+---------+----------------+");
        System.out.println("| Posição |         Índice |");
        System.out.println("+---------+----------------+");
        for(i = 0; i < tabIndices.length; i++){
            System.out.format ("| %7d | %14d |\n", i, tabIndices[i]);
        }    

        System.out.println("+---------+----------------+");
        MsgVetor.enter();
        
    }

    public void buscaSequencial_Indexada(int elemento)
    {
        MsgVetor.msg_central ("Processo - Busca Sequencial Indexada");
        Estatistica e = new Estatistica();
        long tempoInicial = System.currentTimeMillis();
        
         int index = 0, tamanho = this.getTamanho(), i = 0, j = 0, k = 0, ok = 0;
         int[] tabIndices = new int[100];
         
         index = elemento;
        
         
        tabIndices = tabela_Indices(0, 0, vetor, 100, 1000);
        
        
        
        for(i = 0; i< tabIndices.length; i++){
            e.interacoes++;
            e.condicoes++;
            if (tabIndices[0] == index){
                System.out.println("+---------+-------------------+");
                System.out.println("| Posicao | Número Encontrado |");
                System.out.println("+---------+-------------------+");
                System.out.format ("| %7d | %17d |\n", 0, this.vetor[0]);
                System.out.println("+---------+-------------------+");
                ok = 1;
                break;
                              
            }
            else if (tabIndices[i] > index){
                int posx = 1000*(i-1);
                for(k = 0, j = tabIndices[i-1]; k < 1000; k++, posx++) {
                    e.interacoes++;
                    e.condicoes++;
                    if (vetor[posx] == index) {
                    //Se encontrou o elemento, imprime ele na tela e para a pesquisa.
                        System.out.println("+---------+-------------------+");
                        System.out.println("| Posicao | Número Encontrado |");
                        System.out.println("+---------+-------------------+");
                        System.out.format ("| %7d | %17d |\n", posx, this.vetor[posx]);
                        System.out.println("+---------+-------------------+");
                        ok = 1;
                        break;
                    }
                }
                break;
            }
            else if (tabIndices[99] < index) {
                int posx = 990000;
                for(k = 0; k < 1000; k++, posx++) {
                    e.condicoes++;
                    e.interacoes++;
                    if (vetor[posx] == index) {
                    //Se encontrou o elemento, imprime ele na tela e para a pesquisa.
                        System.out.println("+---------+-------------------+");
                        System.out.println("| Posicao | Número Encontrado |");
                        System.out.println("+---------+-------------------+");
                        System.out.format ("| %7d | %17d |\n", posx, this.vetor[posx]);
                        System.out.println("+---------+-------------------+");
                        ok = 1;
                        break;
                    }
                }
                break;
            }

        }
        e.condicoes++;
        if(ok == 0)
        System.out.println("Não encontrou o número " + index);
        
        long tempoFinal = System.currentTimeMillis();
        e.mostrar(this.getTamanho(), tempoInicial, tempoFinal);
    }
    
    public void buscaSequencial_Simples(int elemento)
    {
        MsgVetor.msg_central ("Processo - Busca Sequencial Simples");
        Estatistica e = new Estatistica();
        long tempoInicial = System.currentTimeMillis();
        
         int index = 0, tamanho = this.getTamanho(), i = 0, j = 0, k = 0, ok = 0;
        
         index = elemento;

        
        for(i = 0; i< tamanho; i++){
            e.interacoes++;
            e.condicoes++;
            if (vetor[i] == index){
                System.out.println("+---------+-------------------+");
                System.out.println("| Posicao | Número Encontrado |");
                System.out.println("+---------+-------------------+");
                System.out.format ("| %7d | %17d |\n", i, this.vetor[i]);
                System.out.println("+---------+-------------------+");
                ok = 1;
                break;
            }
        }
          
        e.condicoes++;
        if(ok == 0)
        System.out.println("Não encontrou o número " + index);
        
        long tempoFinal = System.currentTimeMillis();
        e.mostrar(this.getTamanho(), tempoInicial, tempoFinal);
    }
    
    
    public void buscaDreco(int elemento){
        MsgVetor.msg_central ("Processo - Busca Dreco");
        
        contadores =  trocadores = interadores = 0;
        long tempoInicial = System.currentTimeMillis();
        Estatistica e = new Estatistica();
        
        int tamanho = this.getTamanho();
        
        recursivaBuscaDreco(vetor, 0, tamanho - 1, elemento);
        
        e.condicoes = contadores;
        e.interacoes = interadores;
        e.trocas = trocadores;

        long tempoFinal = System.currentTimeMillis();
        e.mostrar(tamanho, tempoInicial, tempoFinal);
        
    }
    
    
    public static int recursivaBuscaDreco(int[] array, int menor, int maior, int chave){
        
        int media = (maior + menor) / 2;
	int valorMeio = array[media];
        
        
	if (menor > maior){
            contadores = contadores + 1;
            System.out.println("- Número não encontrado.");
            return -1; 
        }
	else if(valorMeio == chave){
            contadores = contadores + 2;
            System.out.println("+---------+-------------------+");
            System.out.println("| Posicao | Número Encontrado |");
            System.out.println("+---------+-------------------+");
            System.out.format ("| %7d | %17d |\n", media, array[media]);
            System.out.println("+---------+-------------------+");
            return media;
        }
	else if (valorMeio < chave){
            contadores = contadores + 3;
            return recursivaBuscaDreco(array, media+1, maior, chave);
        }
        else{
            contadores = contadores + 4;
            return recursivaBuscaDreco(array, menor, media-1, chave);
        }
        
        
    }


 
    // Objetivo: carregar/salvar dados em arquivos
    
    public String xarq_escolhe_nome_arquivo (String texto)
    {
        Scanner s = new Scanner(System.in);
        String dados = " ";

        do
        {
            MsgVetor.limpatela();

            try
            {
                MsgVetor.msg_nl ("Digite o nome do arquivo para carga da ["+texto+"] ou x para não dar carga - não pode ser vazio: ");
                dados = s.nextLine();
            }
            catch(Exception e)
            {
                MsgVetor.msg_nl ("Erro de digitação, não deixe a informação vazia:");
            }
        } while (dados.isEmpty());

        return dados;
    }
    
    public int xarq_tamanho_arquivo(String nome_arquivo)
    {
        //
        // Esta função lê o arquivo de dados e conta quantos registros ele possui.
        // A quantidade de registros é retornada e poderá ser utilizada como argumento
        // na criação do vetor de trabalho
        //
        int tamanho = 0;
        boolean prossegue = true;

        FileInputStream stream = null;

        try
        {
            stream = new FileInputStream(nome_arquivo);
            
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(streamReader);
            String line = null;
            MsgVetor.msg_central ("Arquivo aberto!");
            //
            // Levantamento do tamanho do arquivo em termos de linhas
            //
            try
            {
                while ((line = reader.readLine()) != null)
                {
                    tamanho++;
                }

                try
                {
                    reader.close();
                }
                catch (IOException ioe)
                {
                    MsgVetor.msg_central ("Erro no fechamento do arquivo de leitura!");
                }

                try
                {
                    streamReader.close();
                }
                catch (IOException ioe)
                {
                    MsgVetor.msg_central ("Erro no fechamento do arquivo de stream reader!");
                }

                try
                {
                    stream.close();
                }
                catch (IOException ioe)
                {
                    MsgVetor.msg_central ("Erro no fechamento do arquivo stream!");
                }
            }
            catch (IOException ioe)
            {
                prossegue = false;
                MsgVetor.msg_central ("Arquivo com problemas na leitura!");
            }
        }
        catch (FileNotFoundException fe)
        {
            MsgVetor.msg_central ("Arquivo não encontrado!");
            prossegue = false;
        }

        return tamanho;
    }

    public int xarq_abre_arquivo (String texto)
    {
        //
        // Esta função lê o arquivo de dados e cria um vetor cujo tamanho é a
        // quantidade de registros do arquivo. O vetor, após ser populado, é devolvido
        // como resposta para o processo chamador.
        //
        MsgVetor.msg_central ("Preparação do arquivo de entrada para carga da ["+texto+"]");

        String nome_arquivo = this.xarq_escolhe_nome_arquivo(texto);

        int tv = 0;

        if (nome_arquivo.equals("x"))
        {
            MsgVetor.msg_central ("Não será utilizado arquivo para entrada de dados para a ["+texto+"].");
        }
        else
        {
            tv = this.xarq_tamanho_arquivo(nome_arquivo);

            if (tv != 0)
            {
                int i = 0;

                boolean prossegue = true;

                if (prossegue)
                {
                    MsgVetor.msg_central ("Gravando dados na ["+texto+"]......");

                    try
                    {
                        FileInputStream stream = null;
                        InputStreamReader streamReader;
                        BufferedReader reader;
                        String line = null;
                        stream = new FileInputStream(nome_arquivo);
                        streamReader = new InputStreamReader(stream);
                        reader = new BufferedReader(streamReader);
                        line = null;
                        ArrayList<String> linha = new ArrayList<String>();

                        if (prossegue)
                        {
                            try
                            {
                                while ((line = reader.readLine()) != null)
                                {
                                    String[] elemento = line.split(";");

                                    MsgVetor.msg_carga ("...Carregando Posição: ["+i+"] - Número: ["+elemento[0]+"]");

                                    this.setPosicao(i, Integer.valueOf(elemento[0]));

                                    i++;
                                }
                            }
                            catch (IOException ioe)
                            {
                                prossegue = false;
                                MsgVetor.msg_central ("Arquivo com problemas na leitura!");
                            }

                            try
                            {
                                reader.close();
                            }
                            catch (IOException ioe)
                            {
                                MsgVetor.msg_central ("Erro no fechamento do arquivo de leitura!");
                            }

                            try
                            {
                                streamReader.close();
                            }
                            catch (IOException ioe)
                            {
                                MsgVetor.msg_central ("Erro no fechamentdo do arquivo de stream reader!");
                            }

                            try
                            {
                                stream.close();
                            }
                            catch (IOException ioe)
                            {
                                MsgVetor.msg_central ("Erro no fechamento do arquivo stream!");
                            }
                        }
                        else
                        {
                            tv = 0;
                        }
                    }
                    catch (FileNotFoundException fe)
                    {
                        prossegue = false;
                        MsgVetor.msg_central ("Arquivo não encontrado!");
                    }
                }

                MsgVetor.msg_central ("Tamanho do arquivo utilizado para a ["+texto+"]: ["+(i+1)+"] linhas!");
            }
        }
        MsgVetor.enter();
        
        return tv;
    }

    public void xarq_salvar_arquivo (String texto)
    {
        //
        // Esta função recebe um vetor populado e, para cada posição (coluna), grava
        // um registro no arquivo de resposta, cujo nome é informado na função.
        //
        MsgVetor.msg_central ("Gravando arquivo de saída da ["+texto+"]");

        String nome_arquivo = this.xarq_escolhe_nome_arquivo(texto);

        int t = nome_arquivo.length();
        
        if ((nome_arquivo.contains("x")) && (t == 1))
        {
            MsgVetor.msg_central ("Não será utilizado arquivo para gravação de dados da ["+texto+"].");
        }
        else
        {
            FileWriter fileWriter = null;

            try
            {
                fileWriter = new FileWriter(nome_arquivo);
            }
            catch (IOException ex)
            {
                MsgVetor.msg_central ("Erro na gravação!");
            }

            PrintWriter writer = new PrintWriter(fileWriter);

            int i = 0;

            do
            {
                writer.write(this.getPosicao(i)+"\n");
                i++;
            }
            while (i < this.limite);

            writer.close();

            try
            {
                fileWriter.close();
                MsgVetor.msg_central ("Arquivo gravado da ["+texto+"] com ["+this.limite+"] linhas!");
            }
            catch (IOException ex)
            {
                MsgVetor.msg_central ("Erro no fechamento do arquivo!");
            }
        }
    }
    
    void consultar_geral()
    {
        System.out.println();
        System.out.println("+--------------------------+");
        System.out.format ("| Limite do Vetor: %7d |\n", this.limite);
        System.out.println("+---------+----------------+");
        System.out.println("| Posicao |         Número |");
        System.out.println("+---------+----------------+");
            
        for (int posicao = 0; posicao < this.limite; posicao++)
        {
            System.out.format ("| %7d | %14d |\n", posicao, this.vetor[posicao]);
        }    

        System.out.println("+---------+----------------+");
        MsgVetor.enter();
    }
    
    void consultar_posicao(int posicao)
    {
        System.out.println();
        System.out.println("+--------------------------+");
        System.out.format ("| Limite do Vetor: %7d |", this.limite);
        System.out.println("+---------+----------------+");
        System.out.println("| Posicao |         Número |");
        System.out.println("+---------+----------------+");
        System.out.format ("| %7d | %14d |", posicao, this.vetor[posicao]);
        System.out.println("+---------+----------------+");
    }
    
    void consultar_conteudo(int conteudo)
    {
        int posicao = 0;
        
        System.out.println();
        System.out.println("+--------------------------+");
        System.out.format ("| Limite do Vetor: %7d |", this.limite);
        System.out.println("+---------+----------------+");
        System.out.println("| Posicao |         Número |");
        System.out.println("+---------+----------------+");
        
        for (posicao = 0; (posicao < this.limite) && (this.vetor[posicao] != conteudo); posicao++)
        {
        }
        
        if (posicao != this.limite)
        {
            System.out.format ("| %7d | %14d |", posicao, this.vetor[posicao]);
        }
        else
        {
            System.out.println("| Conteúdo não existente.  |");
        }
        System.out.println("+---------+----------------+");
    }

    public VetorGeral()
    {
        int i = 0;
        
        for (i = 0; i < 1000000; i++)
        {
            vetor[i] = 0;
        }
        
        tamanho = 0;
        limite  = 0;        
    }

    

}