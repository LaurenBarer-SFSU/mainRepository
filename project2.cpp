#include <iostream>
#include<string.h>

using namespace std;

// #3 GetNumOfNonWSCharacter gets the number non white space letters.
int GetNumOfNonWSCharacters(string strng) {
   int getnum = 0;
   int s = 0;
   
    while(strng[s] != '\0') {
     
          if(strng[s] != ' ') {
            getnum += 1;
          }
        s++;
   }
   return getnum;
}

// #4 Returns how many words there are in the sentence
int GetNumOfWords(string strng) {
   int getNum = 1;
   int s = 0;
   
            while(strng[s] != '\0') {
       
       if(strng[s] == ' ' && strng[s-1] != ' ') {
                  getNum += 1;
      }
      
      s++;
   }
   return getNum;
}


//#5 finds how many times the word comes up using the number of word times, numWord
int FindText(string numWord, string strng) {
   int s = 0;
   int getNum = 0;
   
   while(strng[s]  !=  '\0') {
      
      if(strng[s]  ==  numWord[0]) {
                  int a = s;
                  int b = 0;
   while(numWord[b] != '\0') {
            
      if(numWord[b] != strng[a]) {
               break;
            }
      if(numWord[b+1] == '\0') {
            getNum += 1;
            }
               a++;
               b++;
         }
      }
      s++;
   }
   return getNum;
}


// #6 replaces all exclamation marks with a period, if any in sentence
void ReplaceExclamation(string& sentence)////////////////////////////////

{
    string newSentence = sentence;

     int s, len = sentence.size();

     for (s = 0; s<len; s++)

     {

          //Replacing ! with .

          if (sentence[s] == '!')

              newSentence[s] = '.';

     }

     sentence = newSentence;

}


// #7  removes extra spaces if any
  void ShortenSpace(string strng) {
  
   int s = 0;
   int b = 0;
  
   string replace;
   while(strng[s] != '\0') {
      if(strng[s] != ' ') {
         replace[b] = strng[s];
         b++;
      }
      else {
         if(replace[b-1] != ' ') {
            replace[b] = ' ';
            b++;
         }
         
      }
      s++;
   }
   replace[b] = '\0';

   // display resulting string
   int a = 0;
   cout << "Edited Text:\n" << endl;
   while(replace[a] != '\0') {
      cout << replace[a];
      a++;
   }
   cout << endl;
}



// #8 Displays the menu options for the user
void PrintMenu(string strng) {
   while(1) {
//menu is:
      cout << "MENU" << endl;
      
      cout << "c - Number of non-whitespace characters" << endl;
      
      cout << "w - Number of words" << endl;
      
      cout << "f - Find text" << endl;
      
      cout << "r - Replace all !'s" << endl;
      
      cout << "s - Shorten spaces" << endl;
     
      cout << "q - Quit" << endl;


                  char choice;
                         
                       cout << "Choose an option:  ";
                     
                       cin >> choice;
      //Cont using while to go through the options
    
      while(1) {
        
        if(choice != 'c'  &&  choice != 'w'  &&  choice != 'r'  &&  choice != 's'  &&  choice != 'q'  &&  choice != 'f') {
         
                     cout << "Choose an option: ";
                    
                     cin >> choice;
         }
         
         else
         
         {
            break;
       
         }
     
      }
      

      // initiate all of the choices the user has and make sure the choices work
      if(choice == 'q') 
      {
         break;
      }

      else if(choice == 'c') 
      
      {
         int nonWPchar = GetNumOfNonWSCharacters(strng);
          
             cout << "Number of non-whitespace characters: " << nonWPchar << endl;
      }

      else if(choice == 'w') 
      {
         int numofWords = GetNumOfWords(strng);
                      cout << "Number of words: " << numofWords << endl;
      }

      else if(choice == 'f') 
      {
         string words;
                     
                              cin.ignore();
         getline(cin, words);
   
    int getW = FindText(words, strng);///////////////////////////////////////////////
         
         cout << "Number of words or phrases found: " << getW << endl;
      }

      else if(choice == 'r') 
      
      {
        
         ReplaceExclamation(strng);
      }

      else 
      {
         ShortenSpace(strng);
      }

      cout << endl;
   }
}



int main() {

   
   string sentence;
   
                  cout << "Enter sample text:" << endl;
   
   getline(cin, sentence);
  
                   cout << "You entered:\n" << sentence << endl;

   
  PrintMenu(sentence);

   
    return 0;
}
