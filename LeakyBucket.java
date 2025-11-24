#include <stdio.h>

int main() {
    int bucket_size, output_rate, incoming, bucket = 0, time = 1;

    printf("Enter bucket capacity: ");
    scanf("%d", &bucket_size);

    printf("Enter output rate: ");
    scanf("%d", &output_rate);

    // Repeat until user enters 0 packets (to stop)
    while (1) {
        printf("\nTime %d sec - Enter incoming packets (0 to stop): ", time);
        scanf("%d", &incoming);

        if (incoming == 0)
            break;

        // Add incoming packets
        bucket += incoming;

        // Check for overflow
        if (bucket > bucket_size) {
            printf("Bucket overflow! Packets dropped: %d\n", bucket - bucket_size);
            bucket = bucket_size; // retain only what fits
        }

        // Send out packets (leak)
        int sent = (bucket >= output_rate) ? output_rate : bucket;
        bucket -= sent;

        printf("Packets sent: %d | Packets left in bucket: %d\n", sent, bucket);
        time++;
    }

    // Leak remaining packets if any
    while (bucket > 0) {
        int sent = (bucket >= output_rate) ? output_rate : bucket;
        bucket -= sent;
        printf("\nLeaking... Packets sent: %d | Packets left: %d", sent, bucket);
    }

    printf("\n\nTransmission complete!\n");
    return 0;
}
