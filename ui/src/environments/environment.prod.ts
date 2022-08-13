// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.
import config from '../../auth_config.json';

const { domain, clientId, audience } = config as {
  domain: string;
  clientId: string;
  audience?: string;
};

export const environment = {
  production: true,
  apiUrl: 'https://city-search-api-springboot.herokuapp.com/api/v1',
  auth: {
    domain,
    clientId,
    audience
  },
  httpInterceptor: {
    allowedList: [`https://city-search-api-springboot.herokuapp.com/api/v1/cities/*`],// updated API endpoint-we need token here only
  },
};
