#include <string>
#include <sstream>
#include <iostream>
#include <vector>
#include <fstream>
#include <list>

struct node{
    char item;
    int price;
    node *next;
};

    void push(struct node** head_ref, int price ,char item)
{

    /* 1. allocate node */
    node* hotel_name = (node*) new(node);
  
    /* 2. put in the data  */
    hotel_name->price  = price;
    hotel_name->item  = item;
  
    /* 3. Make next of new node as head */

    

    hotel_name->next = (*head_ref);
  
    /* 4. move the head to point to the new node */
    (*head_ref)    = hotel_name;

}


int main()
{
    node *hotel_name[5]={0};

    using namespace std;

    ifstream in("data.csv");

    string line, field;

    vector< vector<string> > array;  // the 2D array
    vector<string> v;                // array of values for one line only

    while ( getline(in,line) )    // get next line in file
    {
        v.clear();
        stringstream ss(line);

        while (getline(ss,field,','))  // break line into comma delimitted fields
        {
            v.push_back(field);  // add each field to the 1D array
        }

        array.push_back(v);  // add the 1D array to the 2D array
    }

   

    for (int i=0; i<array.size(); ++i)
    {
        for (int j=0; j<array[i].size(); ++j)
        {
            cout << array[i][j] << "|"; // (separate fields by |)
            
        }
        cout << "\n";
    }
    

    return 0;
}

