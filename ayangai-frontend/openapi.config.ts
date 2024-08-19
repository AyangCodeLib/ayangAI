const { generateService } = require("@umijs/openapi");

generateService({
  requestLibPath: "import request from '@/request'",
  schemaPath: "http://127.0.0.1:8101/api/v2/api-docs",
  serversPath: "./src",
});
