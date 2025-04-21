#include basin-control/lib.h

#include <unistd.h>
#include <stdlib.h>

#define EXIT_ON_ERROR(exp) if ((exp) < 0) {     perror(# exp);     return -1; }

int main()
{
    EXIT_ON_ERROR(basic_control_init());
    EXIT_ON_ERROR(basic_control_auth(./new_secret.txt));

    float good_capi_temperature = 23.5;
    EXIT_ON_ERROR(basic_control_set_water_heater_target_temp(good_capi_temperature));
    EXIT_ON_ERROR(basic_control_do_restart());

    float current_capi_temperature = basic_control_get_water_temp();
    while (current_capi_temperature < good_capi_temperature)
    {
        printf(Капибары всё ещё мёрзнут при %f °Cn, current_capi_temperature);
        sleep(1);
        current_capi_temperature = basic_control_get_water_temp();
    }

    printf(Ура! t=%f °C! Капибары чилятn, current_capi_temperature);

    return 0;
} 
