#include <stdio.h>
#include <stdlib.h>
#include <string.h>

unsigned int ipToInt(char* ip) {
    unsigned int a, b, c, d;
    sscanf(ip, "%u.%u.%u.%u", &a, &b, &c, &d);
    return (a << 24) | (b << 16) | (c << 8) | d;
}

void intToIp(unsigned int ip, char* buffer) {
    sprintf(buffer, "%u.%u.%u.%u", (ip >> 24) & 0xFF, (ip >> 16) & 0xFF, 
            (ip >> 8) & 0xFF, ip & 0xFF);
}

unsigned int calculateSubnetMask(int prefixLength) {
    return prefixLength == 0 ? 0 : ~((1 << (32 - prefixLength)) - 1);
}

int main() {
    char ip[16], buffer[16];
    int prefixLength, newPrefixLength;
    unsigned int subnetMask, newSubnetMask, ipInt;

    printf("Enter IP address: ");
    scanf("%s", ip);
    printf("Enter current prefix length: ");
    scanf("%d", &prefixLength);

    newPrefixLength = prefixLength + 1;
    ipInt = ipToInt(ip);

    subnetMask = calculateSubnetMask(prefixLength);
    newSubnetMask = calculateSubnetMask(newPrefixLength);

    int hostsPerSubnet = (1 << (32 - newPrefixLength)) - 2;

    printf("\nNumber of subnets: 2\n");
    printf("Number of hosts per subnet: %d\n", hostsPerSubnet);

    for (int i = 0; i < 2; i++) {
        unsigned int subnetNetwork =
            (ipInt & subnetMask) | (i << (32 - newPrefixLength));

        unsigned int subnetBroadcast = subnetNetwork | ~newSubnetMask;

        unsigned int firstHost = subnetNetwork + 1;
        unsigned int lastHost = subnetBroadcast - 1;

        printf("\nSubnet %d:\n", i + 1);

        printf("Network Address: ");
        intToIp(subnetNetwork, buffer);
        printf("%s\n", buffer);

        printf("Broadcast Address: ");
        intToIp(subnetBroadcast, buffer);
        printf("%s\n", buffer);

        printf("Subnet Mask: ");
        intToIp(newSubnetMask, buffer);
        printf("%s\n", buffer);

        printf("First Host: ");
        intToIp(firstHost, buffer);
        printf("%s\n", buffer);

        printf("Last Host: ");
        intToIp(lastHost, buffer);
        printf("%s\n", buffer);
    }
    return 0;
}
