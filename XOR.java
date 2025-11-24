#include <stdio.h>

int main() {
    char str[100];
    int i;

    // Taking user input
    printf("Enter a string: ");
    fgets(str, sizeof(str), stdin);  // reads a line of text including spaces

    printf("\nOriginal String: %s\n", str);
    printf("Character\tASCII\tAND(127)\tXOR(127)\n");
    printf("---------------------------------------------\n");

    // Processing each character
    for (i = 0; str[i] != '\0'; i++) {
        if (str[i] == '\n')  // skip newline character from fgets
            continue;

        char and_result = str[i] & 127;  // Bitwise AND
        char xor_result = str[i] ^ 127;  // Bitwise XOR

        printf("    %c\t\t%d\t   %d\t\t   %d\n", str[i], str[i], and_result, xor_result);
    }

    return 0;
}
