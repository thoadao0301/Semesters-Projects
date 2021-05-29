#include<stdio.h>
#include<windows.h>
#include<stdlib.h>
#include<time.h>
// tao bien man hinh
enum display
{
	   MENU=0, INGAME , SETTING, GAMEOVER, INFO
} display;
int snake[1000];
//bien van toc
int vx = 0, vy = -1;
// tao bien thoi gian
int ct = 0;
//chieu dai cua con ran
int length;
//hoa qua
int fruit = 102013;
// diem
int score = 0;
//toc do
int speed = 5000;
//tao fruit
void reCreateFruit()
{
	srand((int)time(NULL));
	int i, check = 0;
		do
		{
			int x = 9 + rand()% 100;
		    int y = 2 + rand()% 26;
		    fruit = 100000 + x*100 + y;
			for(i=0;i<length;i++)
			{
				if(snake[i]==fruit) check = 1;
			}
		}while(check == 1);
}
// tao toa do cho con ran
void intiSnake()
{
	length = 8;
	snake[0] = 105614;
	snake[1] = 105615;
	snake[2] = 105616;
	snake[3] = 105617;
	snake[4] = 105618;
	snake[5] = 105619;
	snake[6] = 105620;
	snake[7] = 105621;
}
int getX(int a)
{
	return (a-100000)/100;
}
int getY(int a)
{
	return a%100;
}
void gotoxy(int x, int y)
{
	static HANDLE h = NULL;
	if(!h)
	    h = GetStdHandle(STD_OUTPUT_HANDLE);
	COORD c = {x,y};
	SetConsoleCursorPosition(h,c);
}
void SetColor(WORD color)
{ 
    HANDLE hConsoleOutput;
    hConsoleOutput = GetStdHandle(STD_OUTPUT_HANDLE);
    CONSOLE_SCREEN_BUFFER_INFO screen_buffer_info;
    GetConsoleScreenBufferInfo(hConsoleOutput, &screen_buffer_info);
    WORD wAttributes = screen_buffer_info.wAttributes;
    color &= 0x000f;
    wAttributes &= 0xfff0; wAttributes |= color;
    SetConsoleTextAttribute(hConsoleOutput, wAttributes);
}
void PrintBoxInGame()
{
	int i;
	for(i=0;i<55;i++)
	{
		SetColor(11);
		printf(" #");
	}
	int j;
	for(j=0;j<28;j++)
	{
		printf("\n");
		for(i=0;i<110;i++)
		{
			SetColor(11);
			printf((i==1 || i == 109)?"#":" ");
		}
	}
	printf("\n");
	for(i=0;i<55;i++)
	{
		SetColor(11);
		printf(" #");
	}
}
    void menu()
    {
        gotoxy(40,10);
        printf("===The mini snake game===");
        gotoxy(47,12);
        printf("PLAY");
        gotoxy(47,13);
        printf("SETTING");
        gotoxy(47,14);
        printf("BEST PLAYER");
        gotoxy(45,12);
        printf("%c",16);
        int key;
        int choice=1;
        int oldchoice = 1;
        while (display == MENU)
        {
        	if(kbhit())
			{
			    key = getch();
			    if(key==80)
			    {
			    	choice++;
			    	if(choice==4) choice = 1;
				}
				if(key == 72)
				{
					choice--;
					if(choice==0) choice = 3;
				}
				switch(choice)
				{
					case 1:
						if(oldchoice != choice)
						{
							gotoxy(45,13);
							printf(" ");
							gotoxy(45,14);
							printf(" ");
							oldchoice = 1;
							gotoxy(45,12);
							SetColor(13);
							printf("%c",16);
						}
						break;
					case 2:
						if(oldchoice != choice)
						{
							gotoxy(45,12);
							printf(" ");
							gotoxy(45,14);
							printf(" ");
							oldchoice = 2;
							gotoxy(45,13);
							SetColor(13);
							printf("%c",16);
						}
						break;
					case 3:
						if(oldchoice != choice);
						{
							gotoxy(45,12);
							printf(" ");
							gotoxy(45,13);
							printf(" ");
							oldchoice = 3;
							gotoxy(45,14);
							SetColor(13);
							printf("%c",16);
						}
						break;
				}
				if(key==13)
				{
					switch(choice)
					{
						case 1:
							display = INGAME;
							break;
						case 2:
							display = SETTING;
							break;
						case 3:
							display = INFO;
							break;
					}
				}
			}
		}
	}
	void setting()
	{
		system("cls");
		SetColor(2);
		gotoxy(45,10);
		printf("SELECT SPEED");
		gotoxy(47,12);
		printf("SLOW");
		gotoxy(47,13);
		printf("MEDIUM");
		gotoxy(47,14);
		printf("FAST");
		gotoxy(40,16);
		printf("Press ENTER to RETURN menu"); 
		gotoxy(45,12);
		printf("%c",16);
		int choice = 1;
		int oldchoice = 1;
		int key;
		while(display==SETTING)
		{
			if(kbhit())
			{
				key = getch();
				if(key==80)
				{
					choice++;
					if(choice==4) choice = 1;
				}
				if(key==72)
				{
					choice--;
					if(choice==0) choice = 3;
				}
				switch(choice)
				{
					case 1:
						if(oldchoice != choice)
						{
							gotoxy(45,13);
						    printf(" ");
						    gotoxy(45,14);
						    printf(" ");
						    oldchoice = 1;
						    gotoxy(45,12);
						    SetColor(4);
						    printf(">");
						}break;
					case 2:
						if(oldchoice != choice)
						{
							gotoxy(45,12);
							printf(" ");
							gotoxy(45,14);
							printf(" ");
							oldchoice = 2;
							gotoxy(45,13);
							SetColor(4);
							printf(">");
						}break;
					case 3:
						if(oldchoice != choice)
						{
							gotoxy(45,12);
							printf(" ");
							gotoxy(45,13);
							printf(" ");
							oldchoice = 3;
							gotoxy(45,14);
							SetColor(4);
							printf(">");
						}break;
				}
				if(key==13)
				{
					switch(choice)
					{
						case 1:
							speed = 5000;
							display = MENU;
							break;
						case 2:
							speed  = 3000;
							display = MENU;
							break;
						case 3:
							speed = 1750;
							display = MENU;
							break;
					}
				}
			}
		}
		system("cls");
	}
	void ingame()
	{
		system("cls");
		int key=0;
		PrintBoxInGame();
		gotoxy(112,10);
		SetColor(14);
		printf("Score: ");
		intiSnake();
		int  j;
		for(j=0;j<length-1;j++)
		{
		  gotoxy(getX(snake[j]),getY(snake[j]));
		  if(j==0) printf("#");
		  else printf("o");
	    }
		while(display  == INGAME)
		{
			if(kbhit())
			{
				key = getch();
				switch(key)
				{
					// nut len
					case 72:
						if(vx!=0){
						vy = -1;
						vx = 0;
					}
						break;
					// nut xuong
					case 80:
						if(vx!=0){
						vy = 1;
						vx = 0;
					}
						break;
					// nut trai
					case 75:
						if(vy!=0){
						vx = -1;
						vy = 0;
					}
						break;
					// nut phai
					case 77:
						if(vy!=0){
						vx = 1;
						vy = 0;
					}	
						break;
				}
			}
			if((ct% speed)==0)
			{
				gotoxy(getX(snake[length-1]),getY(snake[length-1]));
				printf(" ");
				int i;
				for(i=length-1;i>0;i--)
				{
					snake[i] = snake[i-1];
				}
				snake[0] += vx*100;
			    snake[0] += vy;
			    if(getY(snake[0])==0) snake[0] +=27;
			    if(getY(snake[0])==28) snake[0] -= 27;
			    if(getX(snake[0])==1) snake[0] += 107*100;
			    if(getX(snake[0])==109) snake[0] -= 107*100;
			    gotoxy(getX(snake[0]),getY(snake[0]));
			    SetColor(9);
			    printf("#");
			    gotoxy(getX(snake[1]),getY(snake[1]));
			    SetColor(12);
			    printf("o");
			    gotoxy(getX(fruit),getY(fruit));
			    SetColor(9);
			    printf("O");
			    if(snake[0]==fruit)
			    {
			    	gotoxy(getX(fruit),getY(fruit));
			    	printf("#");
			    	length++;
			        reCreateFruit();
			        score += 1;
			        gotoxy(112,13);
			        SetColor(9);
                    printf("%d",score);
				}
			    for(i=1;i<length;i++)
			    {
			    	if(snake[0]==snake[i])
			    	{
			    		system("cls");
			    		display = GAMEOVER;
					}
				}				
			}
			ct++;
		}
	}
	void gameover()
	{
		system("cls");
			gotoxy(43,10);
			SetColor(10);
		    printf("GAME OVER");
		    gotoxy(43,12);
		    SetColor(14);
		    printf("SCORE: %d",score);
		    gotoxy(39,14);
		    SetColor(11);
		    printf("Press ENTER to RETURN");
		    int key = 0;
		    key = getch();
		    if(key==13) display = MENU;
		    system("cls");
		
	}
	void info()
	{
		system("cls");
		SetColor(15);
		int i;
		int a[3];
		int oldscore = 0;
		int currentscore;
		currentscore = score; 
		if(currentscore > oldscore) oldscore = currentscore;
		gotoxy(40,10);
		printf("THE 3 HIGHEST SCORE");
		FILE* f  = fopen("highscore.txt","a");
		fprintf(f, "%d\n",oldscore);
		fclose(f);
		f = fopen("highscore.txt","r");
		for(i=0;i<3;i++)
		{
			fscanf(f,"%d",&a[i]);
		}
		fclose(f);
		for(i=0;i<3;i++);
		{
			int d = 11;
			gotoxy(47,d++);
			printf("%d",a[i]);
		}
		gotoxy(45,15);
		printf("CURRENT SCORE");
		gotoxy(47,16);
		printf("%d",currentscore);
		gotoxy(40,18);
		printf("Press ENTER to RETURN");
		int key = getch();
		if(key==13) display = MENU;
		system("cls");
	}
int main()
{
	// tao vong lap man hinh
	while(1)
	{
		switch (display)
		{
			case MENU:
				menu();
				break;
			case SETTING:
				setting();
				break;
			case INGAME:
				score = 0;
				ingame();
				break;
			case GAMEOVER:
				gameover();
				break;
			case INFO:
				info();
				break;
		}
	}
	return 0;
}
